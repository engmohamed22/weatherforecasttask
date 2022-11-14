package com.example.weatherforecasttask.data.remote.dto

import com.example.weatherforecasttask.data.local.entities.CurrentEntity
import com.squareup.moshi.Json

data class Current(
    val clouds: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    val dt: Int,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    @Json(name = "wind_deg")
    val windDeg: Int,
    @Json(name = "wind_gust")
    var windGust: Double?,
    @Json(name = "wind_speed")
    val windSpeed: Double
) {

    fun mapToCurrentEntity(timezoneOffSet: Int): CurrentEntity {
        return CurrentEntity(
            dt,
            humidity,
            pressure,
            sunrise,
            sunset,
            temp,
            weather[0],
            windSpeed,
            timezoneOffSet
        )
    }
}