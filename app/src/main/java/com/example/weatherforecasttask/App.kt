package com.example.weatherforecasttask

import android.app.Application
import com.example.weatherforecasttask.common.TimberLoggingTree
import com.example.weatherforecasttask.di.components.AppComponent
import com.example.weatherforecasttask.di.components.DaggerAppComponent
import timber.log.Timber


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberLoggingTree())
        appComponent = DaggerAppComponent.builder()
            .application(this).build()
        appComponent.inject(this)
    }
}