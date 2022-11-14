package com.example.weatherforecasttask.presentation.forecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherforecasttask.presentation.weatherDetailsTabDay.DayWeatherDetailsFragment


class ForecastPageAdapter(fragment: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragment, lifecycle) {

    override fun getItemCount(): Int {
        return WeatherTabTypes.values().size
    }

    private var fragmentList = arrayListOf<Fragment>(
        DayWeatherDetailsFragment.newInstance(WeatherTabTypes.TODAY),
        DayWeatherDetailsFragment.newInstance(WeatherTabTypes.TOMORROW),
        DayWeatherDetailsFragment.newInstance(WeatherTabTypes.LATTER)
    )

    override fun createFragment(position: Int): Fragment {
//        val weatherTabTypes = WeatherTabTypes.values()[position]
        when (position) {
            0 -> WeatherTabTypes.TODAY
            1 -> WeatherTabTypes.TOMORROW
            2 -> WeatherTabTypes.LATTER
            else -> WeatherTabTypes.TODAY
        }
        return fragmentList[position]
    }
}