package com.example.weatherforecasttask.di.modules

import androidx.lifecycle.ViewModel
import com.example.weatherforecasttask.presentation.forecast.CurrentWeatherForecastViewModel
import com.example.weatherforecasttask.presentation.weatherDetailsTabDay.DayWeatherDetailsViewModel
import com.example.weatherforecasttask.di.AssistedSavedStateViewModelFactory
import com.example.weatherforecasttask.di.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherForecastViewModel::class)
    abstract fun bindCurrentWeatherViewModel(currentWeatherFactory: CurrentWeatherForecastViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(DayWeatherDetailsViewModel::class)
    abstract fun bindDayWeatherDetailViewModel(dayWeatherDetailsViewModel: DayWeatherDetailsViewModel): ViewModel

}