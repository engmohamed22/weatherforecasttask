package com.example.weatherforecasttask.data.remote.dto


import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import com.squareup.moshi.Json

data class Hourly(
    val clouds: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    val dt: Int,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    var rain: Rain?,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    @Json(name = "wind_deg")
    val windDeg: Int,
    @Json(name = "wind_gust")
    val windGust: Double?,
    @Json(name = "wind_speed")
    val windSpeed: Double
) {
    fun mapToHourlyEntity(timezoneOffSet: Int): HourlyEntity {
        return HourlyEntity(dt, humidity, pressure, temp, weather[0], windSpeed, timezoneOffSet)
    }
}