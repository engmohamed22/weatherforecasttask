package com.example.weatherforecasttask.di.modules

import android.app.Application
import com.example.weatherforecasttask.common.Constants
import com.example.weatherforecasttask.common.Constants.GEOCODING_API
import com.example.weatherforecasttask.common.Constants.WEATHER_API
import com.example.weatherforecasttask.data.local.dao.CurrentWeatherDao
import com.example.weatherforecasttask.data.local.dao.DailyWeatherDao
import com.example.weatherforecasttask.data.local.dao.HourlyWeatherDao
import com.example.weatherforecasttask.data.local.dao.LocationDao
import com.example.weatherforecasttask.data.remote.api.GeocodingApi
import com.example.weatherforecasttask.data.remote.api.WeatherForecastApi
import com.example.weatherforecasttask.data.repository.WeatherForecastRepositoryImpl
import com.example.weatherforecasttask.domain.repository.WeatherForecastRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
open class ApiServiceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }


    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }


    @Provides
    @Singleton
    @Named(WEATHER_API)
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.WEATHER_FORECAST_END_POINT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherForecastApiService(@Named(WEATHER_API) retrofit: Retrofit): WeatherForecastApi {
        return retrofit.create(WeatherForecastApi::class.java)
    }

    @Provides
    @Singleton
    @Named(GEOCODING_API)
    fun provideRetrofitGeocode(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.GEOCODING_END_POINT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideWeatherForecastApiServiceGeocoding(@Named(GEOCODING_API) retrofit: Retrofit): GeocodingApi {
        return retrofit.create(GeocodingApi::class.java)
    }

    @Provides
    fun provideWeatherForecastRepo(
        geocodingApi: GeocodingApi,
        weatherForecastApi: WeatherForecastApi,
        currentWeatherDao: CurrentWeatherDao,
        dailyWeatherDao: DailyWeatherDao,
        hourlyWeatherDao: HourlyWeatherDao,
        locationDao: LocationDao
    ): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(
            geocodingApi,
            weatherForecastApi,
            currentWeatherDao, dailyWeatherDao, hourlyWeatherDao, locationDao
        )
    }

}