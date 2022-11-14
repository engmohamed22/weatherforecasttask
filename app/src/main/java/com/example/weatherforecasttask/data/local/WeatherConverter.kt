package com.example.weatherforecasttask.data.local

import androidx.room.TypeConverter
import com.example.weatherforecasttask.data.remote.dto.Weather
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory



class WeatherConverter {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter: JsonAdapter<Weather> = moshi.adapter(Weather::class.java)

    @TypeConverter
    fun fromWeather(weather: Weather): String {
        return jsonAdapter.toJson(weather)
    }

    @TypeConverter
    fun toWeather(weather: String): Weather? {
        return jsonAdapter.fromJson(weather)
    }
}