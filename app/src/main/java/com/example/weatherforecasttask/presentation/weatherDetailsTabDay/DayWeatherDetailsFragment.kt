package com.example.weatherforecasttask.presentation.weatherDetailsTabDay

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecasttask.App
import com.example.weatherforecasttask.databinding.DayWeatherDetailsFragmentBinding
import com.example.weatherforecasttask.presentation.BaseFragment

import com.example.weatherforecasttask.presentation.forecast.WeatherTabTypes

class DayWeatherDetailsFragment : BaseFragment() {
    companion object {
        const val WEATHER_TAB_TYPE = "WEATHER_TAB_TYPE"

        fun newInstance(weatherTabTypes: WeatherTabTypes): DayWeatherDetailsFragment {
            val dayWeatherDetailsFragment = DayWeatherDetailsFragment()
            val args = Bundle()
            args.putString(WEATHER_TAB_TYPE, weatherTabTypes.name)
            dayWeatherDetailsFragment.arguments = args
            return dayWeatherDetailsFragment
        }

    }

    private var weatherTabTypes: WeatherTabTypes? = null


    private fun setWeatherTabType(weatherTabTypes: WeatherTabTypes) {
        this.weatherTabTypes = weatherTabTypes
    }

    private val dailyWeatherAdapter = DailyWeatherAdapter()
    private val hourlyWeatherAdapter = HourlyWeatherAdapter()
    private val viewModel: DayWeatherDetailsViewModel by viewModels()

    private var _binding: DayWeatherDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragmentComponent = (requireNotNull(this.activity).application as App)
            .appComponent.getFragmentComponentFactory().create()
        fragmentComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabTypes = arguments?.getString(WEATHER_TAB_TYPE, WeatherTabTypes.TODAY.name)
            ?.let { WeatherTabTypes.valueOf(it) }
        if (tabTypes != null) {
            setWeatherTabType(tabTypes)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DayWeatherDetailsFragmentBinding.inflate(inflater, container, false)

        binding.rvWeather.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = when (weatherTabTypes) {
                WeatherTabTypes.TODAY -> hourlyWeatherAdapter
                WeatherTabTypes.TOMORROW -> hourlyWeatherAdapter
                WeatherTabTypes.LATTER -> dailyWeatherAdapter
                else -> throw IllegalArgumentException("Adapter type not found")
            }
        }
        weatherTabTypes?.let { viewModel.setWeatherTabType(it) }
        viewModel.hourlyWeather.observe(viewLifecycleOwner) { hourlyList ->
            if (hourlyList != null) {
                hourlyWeatherAdapter.submitList(hourlyList)
            }
        }

        viewModel.dailyWeather.observe(viewLifecycleOwner) { dailyList ->
            if (dailyList != null) {
                dailyWeatherAdapter.submitList(dailyList)
            }
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}