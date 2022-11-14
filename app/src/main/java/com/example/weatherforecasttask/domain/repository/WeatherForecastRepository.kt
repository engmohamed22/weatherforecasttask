package com.example.weatherforecasttask.domain.repository

import com.example.weatherforecasttask.data.local.entities.CurrentEntity
import com.example.weatherforecasttask.data.local.entities.DailyEntity
import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import com.example.weatherforecasttask.data.local.entities.LocationEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


interface WeatherForecastRepository {

    fun searchByCityName(cityName: String): Completable

    fun getCurrentForecast(): Observable<CurrentEntity>

    fun getCurrentLocation(): Observable<LocationEntity>

    fun getTodaysForecast(): Observable<List<HourlyEntity>>

    fun getTomorrowsForecast(): Observable<List<HourlyEntity>>

    fun getFiveDaysForecast(): Observable<List<DailyEntity>>

}