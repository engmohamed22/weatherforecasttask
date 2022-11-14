package com.example.weatherforecasttask.data.remote.dto


import com.example.weatherforecasttask.data.local.entities.DailyEntity
import com.squareup.moshi.Json

data class Daily(
    val clouds: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    val dt: Int,
    @Json(name = "feels_like")
    val feelsLike: FeelsLike,
    val humidity: Int,
    @Json(name = "moon_phase")
    val moonPhase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double?,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    @Json(name = "wind_deg")
    val windDeg: Int,
    @Json(name = "wind_gust")
    val windGust: Double?,
    @Json(name = "wind_speed")
    val windSpeed: Double
) {

    fun mapToDailyEntity(timezoneOffSet: Int): DailyEntity {
        return DailyEntity(dt, pressure, humidity, temp.min, weather[0], windSpeed, timezoneOffSet)
    }
}