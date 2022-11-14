package com.example.weatherforecasttask.data.remote.dto

import com.example.weatherforecasttask.data.remote.dto.Current
import com.example.weatherforecasttask.data.remote.dto.Daily
import com.example.weatherforecasttask.data.remote.dto.Hourly
import com.squareup.moshi.Json

data class OneCallWeatherForecast(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @Json(name = "timezone_offset")
    val timezoneOffset: Int
)