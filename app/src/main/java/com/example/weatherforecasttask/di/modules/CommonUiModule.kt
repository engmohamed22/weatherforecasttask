package com.example.weatherforecasttask.di.modules

import androidx.lifecycle.ViewModel
import com.example.weatherforecasttask.di.AssistedSavedStateViewModelFactory
import dagger.Module
import dagger.multibindings.Multibinds


@Module
abstract class CommonUiModule {
    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>
}