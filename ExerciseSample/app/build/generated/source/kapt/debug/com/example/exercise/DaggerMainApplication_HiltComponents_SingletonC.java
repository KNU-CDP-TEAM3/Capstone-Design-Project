// Generated by Dagger (https://dagger.dev).
package com.example.exercise;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.health.services.client.HealthServicesClient;
import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_Lifecycle_Factory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerMainApplication_HiltComponents_SingletonC extends MainApplication_HiltComponents.SingletonC {
  private final ApplicationContextModule applicationContextModule;

  private final MainModule mainModule;

  private final DaggerMainApplication_HiltComponents_SingletonC singletonC = this;

  private Provider<HealthServicesClient> provideHealthServicesClientProvider;

  private Provider<CoroutineScope> provideApplicationCoroutineScopeProvider;

  private DaggerMainApplication_HiltComponents_SingletonC(
      ApplicationContextModule applicationContextModuleParam, MainModule mainModuleParam) {
    this.applicationContextModule = applicationContextModuleParam;
    this.mainModule = mainModuleParam;
    initialize(applicationContextModuleParam, mainModuleParam);

  }

  public static Builder builder() {
    return new Builder();
  }

  private HealthServicesClient healthServicesClient() {
    return MainModule_ProvideHealthServicesClientFactory.provideHealthServicesClient(mainModule, ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  @SuppressWarnings("unchecked")
  private void initialize(final ApplicationContextModule applicationContextModuleParam,
      final MainModule mainModuleParam) {
    this.provideHealthServicesClientProvider = DoubleCheck.provider(new SwitchingProvider<HealthServicesClient>(singletonC, 0));
    this.provideApplicationCoroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineScope>(singletonC, 1));
  }

  @Override
  public void injectMainApplication(MainApplication mainApplication) {
  }

  @Override
  public ActivityRetainedComponentBuilder retainedComponentBuilder() {
    return new ActivityRetainedCBuilder(singletonC);
  }

  @Override
  public ServiceComponentBuilder serviceComponentBuilder() {
    return new ServiceCBuilder(singletonC);
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private MainModule mainModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_WorkerFactoryModule(
        HiltWrapper_WorkerFactoryModule hiltWrapper_WorkerFactoryModule) {
      Preconditions.checkNotNull(hiltWrapper_WorkerFactoryModule);
      return this;
    }

    public Builder mainModule(MainModule mainModule) {
      this.mainModule = Preconditions.checkNotNull(mainModule);
      return this;
    }

    public MainApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      if (mainModule == null) {
        this.mainModule = new MainModule();
      }
      return new DaggerMainApplication_HiltComponents_SingletonC(applicationContextModule, mainModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MainApplication_HiltComponents.ActivityRetainedC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private ActivityRetainedCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC) {
      this.singletonC = singletonC;
    }

    @Override
    public MainApplication_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonC);
    }
  }

  private static final class ActivityCBuilder implements MainApplication_HiltComponents.ActivityC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MainApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonC, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MainApplication_HiltComponents.FragmentC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MainApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonC, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MainApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MainApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonC, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MainApplication_HiltComponents.ViewC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MainApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonC, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MainApplication_HiltComponents.ViewModelC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public MainApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      return new ViewModelCImpl(singletonC, activityRetainedCImpl, savedStateHandle);
    }
  }

  private static final class ServiceCBuilder implements MainApplication_HiltComponents.ServiceC.Builder {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private Service service;

    private ServiceCBuilder(DaggerMainApplication_HiltComponents_SingletonC singletonC) {
      this.singletonC = singletonC;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MainApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonC, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MainApplication_HiltComponents.ViewWithFragmentC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends MainApplication_HiltComponents.FragmentC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    private HealthServicesManager healthServicesManager() {
      return new HealthServicesManager(singletonC.provideHealthServicesClientProvider.get(), singletonC.provideApplicationCoroutineScopeProvider.get());
    }

    @Override
    public void injectExerciseFragment(ExerciseFragment arg0) {
      injectExerciseFragment2(arg0);
    }

    @Override
    public void injectHeartFragment(HeartFragment heartFragment) {
      injectHeartFragment2(heartFragment);
    }

    @Override
    public void injectNotAvailableFragment(NotAvailableFragment arg0) {
    }

    @Override
    public void injectPrepareFragment(PrepareFragment arg0) {
    }

    @Override
    public void injectStartupFragment(StartupFragment arg0) {
      injectStartupFragment2(arg0);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonC, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }

    @CanIgnoreReturnValue
    private ExerciseFragment injectExerciseFragment2(ExerciseFragment instance) {
      ExerciseFragment_MembersInjector.injectHealthServicesManager(instance, healthServicesManager());
      return instance;
    }

    @CanIgnoreReturnValue
    private HeartFragment injectHeartFragment2(HeartFragment instance) {
      HeartFragment_MembersInjector.injectHealthServicesManager(instance, healthServicesManager());
      return instance;
    }

    @CanIgnoreReturnValue
    private StartupFragment injectStartupFragment2(StartupFragment instance) {
      StartupFragment_MembersInjector.injectHealthServicesManager(instance, healthServicesManager());
      return instance;
    }
  }

  private static final class ViewCImpl extends MainApplication_HiltComponents.ViewC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, View viewParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MainApplication_HiltComponents.ActivityC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonC.applicationContextModule), getViewModelKeys(), new ViewModelCBuilder(singletonC, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(MainViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonC, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonC, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonC, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends MainApplication_HiltComponents.ViewModelC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<MainViewModel> mainViewModelProvider;

    private ViewModelCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam) {
      this.mainViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, viewModelCImpl, 0);
    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, Provider<ViewModel>>of("com.example.exercise.MainViewModel", (Provider) mainViewModelProvider);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(DaggerMainApplication_HiltComponents_SingletonC singletonC,
          ActivityRetainedCImpl activityRetainedCImpl, ViewModelCImpl viewModelCImpl, int id) {
        this.singletonC = singletonC;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.exercise.MainViewModel 
          return (T) new MainViewModel();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends MainApplication_HiltComponents.ActivityRetainedC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    @SuppressWarnings("rawtypes")
    private Provider lifecycleProvider;

    private ActivityRetainedCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC) {
      this.singletonC = singletonC;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.lifecycleProvider = DoubleCheck.provider(new SwitchingProvider<Object>(singletonC, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonC, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return (ActivityRetainedLifecycle) lifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(DaggerMainApplication_HiltComponents_SingletonC singletonC,
          ActivityRetainedCImpl activityRetainedCImpl, int id) {
        this.singletonC = singletonC;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.Lifecycle 
          return (T) ActivityRetainedComponentManager_Lifecycle_Factory.newInstance();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MainApplication_HiltComponents.ServiceC {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(DaggerMainApplication_HiltComponents_SingletonC singletonC,
        Service serviceParam) {
      this.singletonC = singletonC;


    }

    private HealthServicesManager healthServicesManager() {
      return new HealthServicesManager(singletonC.provideHealthServicesClientProvider.get(), singletonC.provideApplicationCoroutineScopeProvider.get());
    }

    @Override
    public void injectExerciseService(ExerciseService arg0) {
      injectExerciseService2(arg0);
    }

    @CanIgnoreReturnValue
    private ExerciseService injectExerciseService2(ExerciseService instance) {
      ExerciseService_MembersInjector.injectHealthServicesManager(instance, healthServicesManager());
      return instance;
    }
  }

  private static final class SwitchingProvider<T> implements Provider<T> {
    private final DaggerMainApplication_HiltComponents_SingletonC singletonC;

    private final int id;

    SwitchingProvider(DaggerMainApplication_HiltComponents_SingletonC singletonC, int id) {
      this.singletonC = singletonC;
      this.id = id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
      switch (id) {
        case 0: // androidx.health.services.client.HealthServicesClient 
        return (T) singletonC.healthServicesClient();

        case 1: // kotlinx.coroutines.CoroutineScope 
        return (T) MainModule_ProvideApplicationCoroutineScopeFactory.provideApplicationCoroutineScope(singletonC.mainModule);

        default: throw new AssertionError(id);
      }
    }
  }
}
