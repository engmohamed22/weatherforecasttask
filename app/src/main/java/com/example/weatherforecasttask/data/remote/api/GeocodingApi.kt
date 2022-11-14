package com.example.weatherforecasttask.data.remote.api

import com.example.weatherforecasttask.data.remote.dto.GeocodingDtoItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface GeocodingApi {

    @GET("direct")
    fun getGeocoding(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("limit") limit: Int = 1
    ): Observable<List<GeocodingDtoItem>>

}