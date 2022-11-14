package com.example.weatherforecasttask.data.remote.api

import com.example.weatherforecasttask.data.remote.dto.OneCallWeatherForecast
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherForecastApi {


    @GET("onecall")
    fun getWeatherForecastByLatitudeLongitude(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") unit: String = "metric",
    ): Observable<OneCallWeatherForecast>

    @GET("forecast?")
    fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    )


}