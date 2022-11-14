package com.example.weatherforecasttask.domain.usecases

import com.example.weatherforecasttask.data.local.entities.CurrentEntity
import com.example.weatherforecasttask.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class GetCurrentForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getCurrentForecast(): Observable<CurrentEntity> {
        return weatherForecastRepository.getCurrentForecast()
    }
}