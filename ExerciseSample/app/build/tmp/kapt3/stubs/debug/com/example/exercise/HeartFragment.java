package com.example.exercise;

import java.lang.System;

/**
 * Fragment showing the exercise controls and current exercise metrics.
 */
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J$\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020!H\u0016J\u001a\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010-\u001a\u00020!H\u0002J\b\u0010.\u001a\u00020!H\u0002J\"\u0010/\u001a\u00020!2\u0018\u00100\u001a\u0014\u0012\u0004\u0012\u000202\u0012\n\u0012\b\u0012\u0004\u0012\u0002040301H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d\u00a8\u00065"}, d2 = {"Lcom/example/exercise/HeartFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/exercise/databinding/ClickedButtonBinding;", "activeDurationUpdate", "Lcom/example/exercise/ActiveDurationUpdate;", "ambientController", "Landroidx/wear/ambient/AmbientModeSupport$AmbientController;", "Landroidx/wear/ambient/AmbientModeSupport;", "binding", "getBinding", "()Lcom/example/exercise/databinding/ClickedButtonBinding;", "cachedExerciseState", "Landroidx/health/services/client/data/ExerciseState;", "chronoTickJob", "Lkotlinx/coroutines/Job;", "healthServicesManager", "Lcom/example/exercise/HealthServicesManager;", "getHealthServicesManager", "()Lcom/example/exercise/HealthServicesManager;", "setHealthServicesManager", "(Lcom/example/exercise/HealthServicesManager;)V", "serviceConnection", "Lcom/example/exercise/ExerciseServiceConnection;", "uiBindingJob", "viewModel", "Lcom/example/exercise/MainViewModel;", "getViewModel", "()Lcom/example/exercise/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindViewsToService", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "startEndExercise", "tryStartExercise", "updateMetrics", "data", "", "Landroidx/health/services/client/data/DataType;", "", "Landroidx/health/services/client/data/DataPoint;", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class HeartFragment extends androidx.fragment.app.Fragment {
    @javax.inject.Inject()
    public com.example.exercise.HealthServicesManager healthServicesManager;
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.exercise.databinding.ClickedButtonBinding _binding;
    private com.example.exercise.ExerciseServiceConnection serviceConnection;
    private androidx.health.services.client.data.ExerciseState cachedExerciseState = androidx.health.services.client.data.ExerciseState.USER_ENDED;
    private com.example.exercise.ActiveDurationUpdate activeDurationUpdate;
    private kotlinx.coroutines.Job chronoTickJob;
    private kotlinx.coroutines.Job uiBindingJob;
    private androidx.wear.ambient.AmbientModeSupport.AmbientController ambientController;
    
    public HeartFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.exercise.HealthServicesManager getHealthServicesManager() {
        return null;
    }
    
    public final void setHealthServicesManager(@org.jetbrains.annotations.NotNull()
    com.example.exercise.HealthServicesManager p0) {
    }
    
    private final com.example.exercise.MainViewModel getViewModel() {
        return null;
    }
    
    private final com.example.exercise.databinding.ClickedButtonBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void startEndExercise() {
    }
    
    private final void tryStartExercise() {
    }
    
    private final void bindViewsToService() {
    }
    
    private final void updateMetrics(java.util.Map<androidx.health.services.client.data.DataType, ? extends java.util.List<androidx.health.services.client.data.DataPoint>> data) {
    }
}