package com.example.weatherforecasttask.domain.usecases

import com.example.weatherforecasttask.domain.repository.WeatherForecastRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject


class SearchForecast @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) {

    fun byCityNameForecast(cityName: String): Completable {
        return weatherForecastRepository.searchByCityName(cityName)
    }

}