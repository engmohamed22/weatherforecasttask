package com.example.weatherforecasttask.presentation.weatherDetailsTabDay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecasttask.data.local.entities.DailyEntity
import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import com.example.weatherforecasttask.domain.usecases.FiveDayForecast
import com.example.weatherforecasttask.domain.usecases.TodaysForecast
import com.example.weatherforecasttask.domain.usecases.TomorrowForecast
import com.example.weatherforecasttask.presentation.forecast.WeatherTabTypes
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DayWeatherDetailsViewModel @Inject constructor(
    private val todaysForecast: TodaysForecast,
    private val tomorrowForecast: TomorrowForecast,
    private val fiveDayForecast: FiveDayForecast
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val dailyWeather = MutableLiveData<List<DailyEntity>>()
    val hourlyWeather = MutableLiveData<List<HourlyEntity>>()

    fun setWeatherTabType(weatherTabTypes: WeatherTabTypes) {
        when (weatherTabTypes) {
            WeatherTabTypes.TODAY -> {
                compositeDisposable.add(
                    todaysForecast.getForecast()
                        .subscribeOn(Schedulers.io())
                        .subscribe(hourlyWeather::postValue, Timber::e)
                )
            }
            WeatherTabTypes.TOMORROW -> {
                compositeDisposable.add(
                    tomorrowForecast.getForecast()
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            Collections.reverse(it)
                            hourlyWeather.postValue(it)
                        }, Timber::e)
                )
            }
            WeatherTabTypes.LATTER -> {
                compositeDisposable.add(
                    fiveDayForecast.getForecast()
                        .subscribeOn(Schedulers.io())
                        .subscribe(dailyWeather::postValue, Timber::e)
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}