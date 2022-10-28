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

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Coordinates messages between [MainActivity] and [ExerciseFragment].
 */
//ViewModel 클래스는 수명 주기를 고려하여 UI 관련 데이터를 저장하고 관리하도록 설계되었습니다.
// ViewModel 클래스를 사용하면 화면 회전과 같이 구성을 변경할 때도 데이터를 유지할 수 있습니다.

//ambientEvent 이건 뭔지
// key press channel -> key 입력 시 발생?
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _ambientEventChannel = Channel<AmbientEvent>(capacity = Channel.CONFLATED)
    val ambientEventFlow = _ambientEventChannel.receiveAsFlow()

    private val _keyPressChannel = Channel<Unit>(capacity = Channel.CONFLATED)
    val keyPressFlow = _keyPressChannel.receiveAsFlow()

    fun sendAmbientEvent(event: AmbientEvent) {
        viewModelScope.launch {
            _ambientEventChannel.send(event)
        }
    }

    fun sendKeyPress() {
        viewModelScope.launch {
            _keyPressChannel.send(Unit)
        }
    }
}

sealed class AmbientEvent {
    class Enter(val ambientDetails: Bundle) : AmbientEvent()
    object Exit : AmbientEvent()
    object Update : AmbientEvent()
}
