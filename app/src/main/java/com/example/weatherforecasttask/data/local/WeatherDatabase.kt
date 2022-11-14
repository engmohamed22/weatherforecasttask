package com.example.weatherforecasttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherforecasttask.data.local.WeatherConverter
import com.example.weatherforecasttask.data.local.dao.CurrentWeatherDao
import com.example.weatherforecasttask.data.local.dao.DailyWeatherDao
import com.example.weatherforecasttask.data.local.dao.HourlyWeatherDao
import com.example.weatherforecasttask.data.local.dao.LocationDao
import com.example.weatherforecasttask.data.local.entities.CurrentEntity
import com.example.weatherforecasttask.data.local.entities.DailyEntity
import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import com.example.weatherforecasttask.data.local.entities.LocationEntity


@Database(
    entities = [CurrentEntity::class, DailyEntity::class, HourlyEntity::class, LocationEntity::class],
    version = 1
)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
    abstract fun getDailyWeatherDao(): DailyWeatherDao
    abstract fun getHourlyWeatherDao(): HourlyWeatherDao
    abstract fun getLocationDao(): LocationDao
}