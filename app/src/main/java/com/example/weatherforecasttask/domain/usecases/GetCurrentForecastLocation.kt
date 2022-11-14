package com.example.weatherforecasttask.domain.usecases

import com.example.weatherforecasttask.data.local.entities.LocationEntity
import com.example.weatherforecasttask.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class GetCurrentForecastLocation @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun getLocation(): Observable<LocationEntity> {
        return weatherForecastRepository.getCurrentLocation()
    }
}