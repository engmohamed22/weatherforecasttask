package com.example.weatherforecasttask.domain.usecases

import com.example.weatherforecasttask.data.local.entities.DailyEntity
import com.example.weatherforecasttask.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class FiveDayForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getForecast(): Observable<List<DailyEntity>> {
        return weatherForecastRepository.getFiveDaysForecast()
    }
}