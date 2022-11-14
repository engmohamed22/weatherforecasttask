package com.example.weatherforecasttask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LocationEntity(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
