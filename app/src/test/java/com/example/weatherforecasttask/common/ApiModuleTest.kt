package com.example.weatherforecasttask.common

import com.example.weatherforecasttask.data.remote.api.WeatherForecastApi
import com.example.weatherforecasttask.di.modules.ApiServiceModule

class ApiModuleTest (val mockApiModule: WeatherForecastApi) : ApiServiceModule(){
    @test
    fun provideWeatherApiService(): WeatherForecastApi {
        return mockApiModule
    }

}

annotation class test
