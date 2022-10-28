/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.exercise

import android.util.Log
import androidx.concurrent.futures.await
import androidx.health.services.client.ExerciseUpdateListener
import androidx.health.services.client.HealthServicesClient
import androidx.health.services.client.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

/**
 * Entry point for [HealthServicesClient] APIs, wrapping them in coroutine-friendly APIs.
 */
class HealthServicesManager @Inject constructor(
    healthServicesClient: HealthServicesClient,
    coroutineScope: CoroutineScope
) {
    //exerciseClient 사용
    private val exerciseClient = healthServicesClient.exerciseClient
    private val measureClient = healthServicesClient.measureClient

    //측정항목의 종류, 설정할 수 있는 운동 목표, 운동 유형에서 제공하는 기타 기능을 확인할 수 있습니다.
    //반환된 ExerciseTypeCapabilities의 내부에 있는 supportedExerciseTypes에는 데이터를 요청할 수 있는 DataType이 나열됩니다.
    // 이는 기기에 따라 다르므로 지원되지 않는 DataType을 요청하지 않도록 주의해야 합니다. 그러지 않으면 요청이 실패할 수 있습니다.
    private var exerciseCapabilities: ExerciseTypeCapabilities? = null
    private var capabilitiesLoaded = false


    //그래서 뭐하는건데?
    //문서에 있음.
    //대충 exerciseCapabilities에 운동 관련 기능같은거 저장해놓는 듯.
    // 앱은 앱 시작 시 기기 기능을 쿼리하고 플랫폼에서 지원하는 운동,
    // 각 운동에 지원되는 기능(예: 자동 일시중지), 각 운동에 지원되는 데이터 유형 및 이러한 데이터 유형 각각에서 요구하는 권한을
    // 저장하고 처리해야 합니다.
    // 특히 여기서는 running 달리기 기능인거 같고, 이 데이터 타입 바꾸면서 뭔가를 하긴 해야할 듯.
    // ? 달리기 기능이 아닌가


    suspend fun getExerciseCapabilities(): ExerciseTypeCapabilities? {
        if (!capabilitiesLoaded) {
            val capabilities = exerciseClient.capabilities.await()
            if (ExerciseType.RUNNING in capabilities.supportedExerciseTypes) {
                exerciseCapabilities =
                    capabilities.getExerciseTypeCapabilities(ExerciseType.RUNNING)
            }
            val capabilities1 = exerciseClient.capabilities.await()

            capabilitiesLoaded = true
        }
        return exerciseCapabilities
    }

    //있는지 없는지?
    suspend fun hasExerciseCapability(): Boolean {
        return getExerciseCapabilities() != null
    }

    //?
    suspend fun isExerciseInProgress(): Boolean {
        val exerciseInfo = exerciseClient.currentExerciseInfo.await()
        return exerciseInfo.exerciseTrackedStatus == ExerciseTrackedStatus.OWNED_EXERCISE_IN_PROGRESS
    }
    //?
    suspend fun isTrackingExerciseInAnotherApp(): Boolean {
        val exerciseInfo = exerciseClient.currentExerciseInfo.await()
        return exerciseInfo.exerciseTrackedStatus == ExerciseTrackedStatus.OTHER_APP_IN_PROGRESS
    }

    //포그라운드 서비스는 앞서 말했다 싶이 사용자에게 눈에 띄는 작업, (실행되고 있어!! 잊지마!! 존재감을 펼치기) 입니다.
    //그니까 여기 있는 함수들은 걍 여기에서만 호출하게 해라.
    /***
     * Note: don't call this method from outside of foreground service (ie. [ExerciseService])
     * when acquiring calories or distance. foreground service 외부에서 이거 호출 ㄴㄴ (칼로리하고 거리 같은거 수집)
     */
    //운동 시작~~~
    //여기서 측정되는거나 목표 다 일회용 저장되는거 아님
    suspend fun startExercise() {
        Log.d(TAG, "Starting exercise")
        // Types for which we want to receive metrics. Only ask for ones that are supported.

        //위에서 얻고
        val capabilities = getExerciseCapabilities() ?: return

        // 데이터 타입인데, capabilities.supportedDataTypes 이게 가능한 데이터 타입들 불러오는 거고,
        // intersect 교집합해서 있냐 없냐를 판단하는 것 같음. 이거는 심박수에 관한 것.
        val dataTypes = setOf(
            DataType.HEART_RATE_BPM,
            DataType.SPEED,
            DataType.SPO2
        ).intersect(capabilities.supportedDataTypes)



        // 마찬가지로, 칼로리 , 거리 같은거 저장.
        val aggDataTypes = setOf(
            DataType.TOTAL_CALORIES,
            DataType.DISTANCE
        ).intersect(capabilities.supportedDataTypes)

        //운동 목표들의 mutable 한 리스트 , 수정이나 삭제가 가능.
        val exerciseGoals = mutableListOf<ExerciseGoal>()
        //칼로리 goal 에 대해서 이야기하고 있는데,. 왜 getExerciseCapabilities 에서 받아온 capa를?
        //그게 calorie랑 뭔 상관? running 안에서 calorie를 의미? 달리기 기능에서의 쓸 수 있는 calorie
        //워치에서 달리면 그것과 연관된 calorie나 , distance가 있다.
        if (supportsCalorieGoal(capabilities)) {
            // Create a one-time goal.
            //리스트에 추가해주기
            exerciseGoals.add(
                ExerciseGoal.createOneTimeGoal(
                    DataTypeCondition(
                        dataType = DataType.TOTAL_CALORIES,
                        threshold = Value.ofDouble(CALORIES_THRESHOLD),
                        comparisonType = ComparisonType.GREATER_THAN_OR_EQUAL
                    )
                )
            )
        }
        //distance에 관한 것.
        //그러니까 running 이라는 capa 에 Calorie 하고 distance 같은 운동 목표 지원해주는가 ?
        // yes)그러면 운동 목표 리스트에 넣어라  no) 뭐
        if (supportsDistanceMilestone(capabilities)) {
            // Create a milestone goal. To make a milestone for every kilometer, set the initial
            // threshold to 1km and the period to 1km.
            exerciseGoals.add(
                ExerciseGoal.createMilestone(
                    condition = DataTypeCondition(
                        dataType = DataType.DISTANCE,
                        threshold = Value.ofDouble(DISTANCE_THRESHOLD),
                        comparisonType = ComparisonType.GREATER_THAN_OR_EQUAL
                    ),
                    period = Value.ofDouble(DISTANCE_THRESHOLD)
                )
            )
        }
        //exercise type 은 running 이고,
        //자동 중지는 못 하게 막아놓은거같고
        //aggregate 여러 개 있는 거 calorie 하고 distance
        //그냥 데이터 타입은 심박수 근데 쟤는 그냥 검사같은거 없이 바로 하네
        // goals 설정하고 .
        // gps 기능 보고
        // 빌드 -> exerciseClient 한테 config 넘겨서 이거 해돌라고 하는 듯
        val config = ExerciseConfig.builder()
            .setExerciseType(ExerciseType.RUNNING)
            .setShouldEnableAutoPauseAndResume(false)
            .setAggregateDataTypes(aggDataTypes)
            .setDataTypes(dataTypes)
            .setExerciseGoals(exerciseGoals)
            // Required for GPS for LOCATION data type, optional for some other types.
            .setShouldEnableGps(true)
            .build()
        exerciseClient.startExercise(config).await()
        //여기까지 운동 관련 기능하는 거 넣는 듯 .
        // 심박수 , 칼로리 , 거리...
        // 여기서 측정가능한 무언가들을 넣으면 될 듯 하다 .
    }

    //supportedGoals와 supportedMilestones 필드는 키가 DataType이고 값이 연결된 DataType과 함께 사용할 수 있는 ComparisonType 집합인 위치에 매핑됩니다.
    // 이를 사용하여 운동이 만들려는 운동 목표를 지원할 수 있는 운동인지 판단합니다.
    //앱에서 사용자가 자동 일시중지 또는 랩 기능을 사용하도록 허용하면 앱은 기기에서 이 기능을 지원하는지 확인해야 합니다.
    // 각 기능에 맞게 supportsAutoPauseAndResume 또는 supportsLaps를 사용합니다.
    // ExerciseClient는 기기에서 지원하지 않는 요청을 거부합니다.
    // 운동 목표? 그런걸 설정해주는 것?
    //capa 받아와서, 지원하는 칼로리 -> running 에서 지원하는 기능 중 하나가 칼로리?
    private fun supportsCalorieGoal(capabilities: ExerciseTypeCapabilities): Boolean {
        val supported = capabilities.supportedGoals[DataType.TOTAL_CALORIES]
        return supported != null && ComparisonType.GREATER_THAN_OR_EQUAL in supported
    }

    private fun supportsDistanceMilestone(capabilities: ExerciseTypeCapabilities): Boolean {
        val supported = capabilities.supportedMilestones[DataType.DISTANCE]
        return supported != null && ComparisonType.GREATER_THAN_OR_EQUAL in supported
    }

    /***
     * Note: don't call this method from outside of [ExerciseService]
     * when acquiring calories or distance.
     */
    //그니까 여기 있는 함수들은 걍 여기에서만 호출하게 해라. 2

    //exercise 준비하라고 ?
    suspend fun prepareExercise() {
        Log.d(TAG, "Preparing an exercise")

        //진짜 준비운동같은 거 하네
        // 위에랑 마찬가지로, exerciseType 설정하고..
        // 심박수하고 위치
        val warmUpConfig = WarmUpConfig.builder()
            .setExerciseType(ExerciseType.RUNNING)
            .setDataTypes(
                setOf(
                    DataType.HEART_RATE_BPM,
                    DataType.LOCATION
                )
            )
            .build()
        //이거 왜 있는거임..? 진짜 준비운동 기능이 있는건가 -> 거리하고 칼로리는 재기 싫고, 그냥 심박수만 체크해주라는
        try {
            exerciseClient.prepareExercise(warmUpConfig).await()
        } catch (e: Exception) {
            Log.e(TAG, "Prepare exercise failed - ${e.message}")
        }
    }
    //운동 끝~~~
    suspend fun endExercise() {
        Log.d(TAG, "Ending exercise")
        exerciseClient.endExercise().await()
    }
    //운동 멈춰
    suspend fun pauseExercise() {
        Log.d(TAG, "Pausing exercise")
        exerciseClient.pauseExercise().await()
    }
    //다시 ㄱ
    suspend fun resumeExercise() {
        Log.d(TAG, "Resuming exercise")
        exerciseClient.resumeExercise().await()
    }
    //뭐임 이건
    suspend fun markLap() {
        if (isExerciseInProgress()) {
            exerciseClient.markLap().await()
        }
    }

    /**
     * A shared flow for [ExerciseUpdate]s.
     *
     * When the flow starts, it will register an [ExerciseUpdateListener] and start to emit
     * messages. When there are no more subscribers, or when the coroutine scope of [shareIn] is
     * cancelled, this flow will unregister the listener.
     *
     * A shared flow is used because only a single [ExerciseUpdateListener] can be reigstered at a
     * time, even if there are multiple consumers of the flow.
     *
     * [callbackFlow] is used to bridge between a callback-based API and Kotlin flows.
     */

    //운동 상태 업데이트 등록
    //운동 업데이트는 리스너로 전송됩니다. 앱은 한 번에 하나의 리스너만 등록할 수 있습니다.
    // 운동을 시작하기 전에 리스너를 설정해야 합니다. 리스너는 앱이 소유한 운동의 업데이트만 수신합니다.
    // listener 부터 문서의 내용이랑 똑같...진 않고 비슷함
    // 뭔가 이건 건들면 안될거 같다는 느낌이 강하게 든다.
    @OptIn(ExperimentalCoroutinesApi::class)
    val exerciseUpdateFlow = callbackFlow<ExerciseMessage> {
        val listener = object : ExerciseUpdateListener {
            override fun onExerciseUpdate(update: ExerciseUpdate) {
                // Process the latest information about the exercise.
                coroutineScope.runCatching {
                    trySendBlocking(ExerciseMessage.ExerciseUpdateMessage(update))
                }
            }

            override fun onLapSummary(lapSummary: ExerciseLapSummary) {
                coroutineScope.runCatching {
                    trySendBlocking(ExerciseMessage.LapSummaryMessage(lapSummary))
                }
            }

            override fun onAvailabilityChanged(dataType: DataType, availability: Availability) {
                if (availability is LocationAvailability) {
                    coroutineScope.runCatching {
                        trySendBlocking(ExerciseMessage.LocationAvailabilityMessage(availability))
                    }
                }
            }
        }
        exerciseClient.setUpdateListener(listener)
        awaitClose {
            exerciseClient.clearUpdateListener(listener)
        }
    }

    //목표치 같은거 여기서 설정해주는 듯.
    private companion object {
        const val CALORIES_THRESHOLD = 250.0
        const val DISTANCE_THRESHOLD = 1_000.0 // meters
    }
}

sealed class ExerciseMessage {
    class ExerciseUpdateMessage(val exerciseUpdate: ExerciseUpdate) : ExerciseMessage()
    class LapSummaryMessage(val lapSummary: ExerciseLapSummary) : ExerciseMessage()
    class LocationAvailabilityMessage(val locationAvailability: LocationAvailability) :
        ExerciseMessage()
}

// 한 앱 당 하나의 운동만 지원한다는 점.
// 여기서 해야할 것 -> 데이터 추가할 떄. 운동 목표 같은거 관리.
