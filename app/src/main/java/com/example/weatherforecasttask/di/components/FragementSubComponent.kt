package com.example.weatherforecasttask.di.components



import com.example.weatherforecasttask.di.FragmentScope
import com.example.weatherforecasttask.di.modules.ViewModelModule
import com.example.weatherforecasttask.presentation.forecast.CurrentWeatherForecastFragment
import com.example.weatherforecasttask.presentation.weatherDetailsTabDay.DayWeatherDetailsFragment
import dagger.Subcomponent



@FragmentScope
@Subcomponent(modules = [ViewModelModule::class])
interface FragmentSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentSubComponent
    }

    fun inject(currentWeatherForecastFragment: CurrentWeatherForecastFragment)
    fun inject(dayWeatherDetailsFragment: DayWeatherDetailsFragment)
}