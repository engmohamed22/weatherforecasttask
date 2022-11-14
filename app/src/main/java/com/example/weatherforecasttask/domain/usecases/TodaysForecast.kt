package com.example.weatherforecasttask.domain.usecases

import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import com.example.weatherforecasttask.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class TodaysForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getForecast(): Observable<List<HourlyEntity>> {
        return weatherForecastRepository.getTodaysForecast()
    }

}