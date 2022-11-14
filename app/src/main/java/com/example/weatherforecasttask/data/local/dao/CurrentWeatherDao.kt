package com.example.weatherforecasttask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.weatherforecasttask.data.local.entities.CurrentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = REPLACE)
    fun addCurrent(currentEntity: CurrentEntity): Completable

    @Query("SELECT * FROM CurrentEntity LIMIT 1")
    fun getCurrent(): Observable<CurrentEntity>

    @Query("DELETE FROM CurrentEntity")
    fun deleteAll(): Completable
}