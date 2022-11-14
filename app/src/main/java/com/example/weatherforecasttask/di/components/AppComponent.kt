package com.example.weatherforecasttask.di.components

import android.app.Application
import com.example.weatherforecasttask.App
import com.example.weatherforecasttask.di.modules.ApiServiceModule
import com.example.weatherforecasttask.di.modules.CommonUiModule
import com.example.weatherforecasttask.di.modules.WeatherDatabaseModule

import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [ApiServiceModule::class, AppSubComponents::class,
        WeatherDatabaseModule::class, CommonUiModule::class]
)
@Singleton
interface AppComponent {

    fun getActivityComponentFactory(): ActivitySubComponent.Factory
    fun getFragmentComponentFactory(): FragmentSubComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}