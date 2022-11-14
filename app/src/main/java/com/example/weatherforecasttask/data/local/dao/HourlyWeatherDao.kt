package com.example.weatherforecasttask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


@Dao
interface HourlyWeatherDao {

    @Insert(onConflict = REPLACE)
    fun addAllHourlyEntity(hourlyEntity: List<HourlyEntity>): Completable

    @Query("SELECT * FROM HourlyEntity LIMIT 24")
    fun getTodayHourlyWeather(): Observable<List<HourlyEntity>>

    @Query("SELECT * FROM HourlyEntity ORDER BY id DESC LIMIT 24")
    fun getTomorrowHourlyWeather(): Observable<List<HourlyEntity>>

    @Query("DELETE FROM HourlyEntity")
    fun deleteAll(): Completable
}