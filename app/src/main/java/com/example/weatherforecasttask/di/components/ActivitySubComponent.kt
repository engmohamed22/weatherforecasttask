package com.example.weatherforecasttask.di.components

import com.example.weatherforecasttask.di.ActivityScope
import com.example.weatherforecasttask.presentation.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent()
interface ActivitySubComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivitySubComponent
    }

    fun inject(mainActivity: MainActivity)

}