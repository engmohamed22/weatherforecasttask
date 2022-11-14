package com.example.weatherforecasttask.presentation.forecast


import android.Manifest
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.weatherforecasttask.App
import com.example.weatherforecasttask.common.Constants
import com.example.weatherforecasttask.common.DateTimeConversion
import com.example.weatherforecasttask.common.GeocodeConverter
import com.example.weatherforecasttask.data.local.entities.LocationEntity
import com.example.weatherforecasttask.R
import com.example.weatherforecasttask.databinding.CurrentWeatherForecastFragmentBinding
import com.example.weatherforecasttask.presentation.BaseFragment
import com.example.weatherforecasttask.presentation.MainActivity
import com.example.weatherforecasttask.presentation.extension.hide
import com.example.weatherforecasttask.presentation.extension.show
import com.example.weatherforecasttask.presentation.extension.snackBar
import com.google.android.gms.location.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber
import java.util.*


class CurrentWeatherForecastFragment : BaseFragment() {

    companion object {
        fun newInstance() = CurrentWeatherForecastFragment()
    }

    private var _binding: CurrentWeatherForecastFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrentWeatherForecastViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var locationCallback: LocationCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragmentComponent = (requireNotNull(this.activity).application as App)
            .appComponent.getFragmentComponentFactory().create()
        fragmentComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val mContext = context
        if (mContext != null) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext)
            buildLocationRequest()
            buildLocationCallBack()
            requestLocationPermission()
            requestLocationUpdate()
        }

    }

    private fun requestLocationUpdate() {
        val mContext = context
        if (mContext != null) {
            if (ActivityCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationCallback?.let {
                    Looper.myLooper()?.let { looper ->
                        fusedLocationClient.requestLocationUpdates(
                            locationRequest,
                            it,
                            looper
                        )
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrentWeatherForecastFragmentBinding.inflate(inflater, container, false)
        val fm: FragmentManager = childFragmentManager
        val lifecycle = viewLifecycleOwner.lifecycle
        val forecastpager = ForecastPageAdapter(fm, lifecycle)
        binding.weatherPager.adapter = forecastpager

        viewModel.currentSearch.observe(viewLifecycleOwner) { currentSearch ->
            if (currentSearch != null) {
                requireNotNull(activity as MainActivity).setToolBarTitle(
                    "${currentSearch.name},${
                        currentSearch.country.lowercase(Locale.getDefault())
                    }"
                )
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                Timber.e(errorMessage)
                binding.root.snackBar(getString(R.string.search_error))
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading != null) {
                showProgressBar(isLoading)
            }
        }

        viewModel.currentWeather.observe(viewLifecycleOwner) { currentWeather ->
            if (currentWeather != null) {
                binding.tvTemp.text = resources.getString(R.string.temp, currentWeather.temp)
                val sunrise = DateTimeConversion.convertToTime(
                    currentWeather.sunrise,
                    currentWeather.timezoneOffSet
                )
                val sunset = DateTimeConversion.convertToTime(
                    currentWeather.sunset,
                    currentWeather.timezoneOffSet
                )
                val lastUpdatedTime = DateTimeConversion.convertToTime(
                    currentWeather.dt,
                    currentWeather.timezoneOffSet
                )
                binding.tvLastUpdateTime.text =
                    resources.getString(R.string.last_updated_time, lastUpdatedTime)
                binding.tvCurrentWeatherDetails.text = resources.getString(
                    R.string.current_weather,
                    currentWeather.windSpeed.toString(),
                    currentWeather.pressure,
                    currentWeather.humidity,
                    sunrise,
                    sunset
                )

                Glide.with(this)
                    .load(String.format(Constants.WEATHER_ICON_URL, currentWeather.weather?.icon))
                    .centerCrop()
//                    .placeholder(R.drawable.loading_spinner)
                    .into(binding.ivWeather)
                binding.ivWeather.contentDescription = resources.getString(
                    R.string.weather_image_description,
                    currentWeather.weather?.description
                )
            }
        }


        val tabTitles = resources.getStringArray(R.array.weather_tab_days)
        TabLayoutMediator(
            binding.tabLayout, binding.weatherPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabTitles[position]
            binding.weatherPager.setCurrentItem(tab.position, true)
        }.attach()


        if (viewModel.searchMutableLiveData.value != null) {
            val search: String = viewModel.searchMutableLiveData.value!!
            viewModel.search(search)
        }

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }

        searchView.queryHint = getString(R.string.search_by_city_name)
        if (viewModel.currentSearch.value != null) {
            val search: LocationEntity = viewModel.currentSearch.value!!
            requireNotNull(activity as MainActivity).setToolBarTitle(
                "${search.name},${
                    search.country.lowercase(Locale.getDefault())
                }"
            )
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.search(it) }
                return true
            }
        })
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 1000
    }

    private fun buildLocationCallBack() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    val latitude = location.latitude.toString()
                    val longitude = location.longitude.toString()

                    val cityName =
                        context?.let { GeocodeConverter.getCityName(it, latitude, longitude) }
                    Timber.e("$latitude $longitude $cityName")
                    cityName?.let { viewModel.initialiseCurrentCity(it) }
                }
            }
        }
    }

    private fun showProgressBar(shouldShowProgress: Boolean) {
        if (shouldShowProgress) {
            binding.progressBar.show()
            binding.clCurrentWeather.hide()
            binding.tabLayout.hide()
            binding.weatherPager.hide()
            return
        }
        binding.progressBar.hide()
        binding.clCurrentWeather.show()
        binding.tabLayout.show()
        binding.weatherPager.show()
    }


    private fun requestLocationPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true -> {
                    // Precise location access granted.
                    requestLocationUpdate()
                    val mContext = context ?: return@registerForActivityResult
                    if (!isLocationEnabled(mContext)) showGPSNotEnabledDialog(mContext)
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> {
                    // Only approximate location access granted.
                    requestLocationUpdate()
                    val mContext = context ?: return@registerForActivityResult
                    if (!isLocationEnabled(mContext)) showGPSNotEnabledDialog(mContext)
                }
                else -> {
                    // No location access granted.
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    }


    private fun showGPSNotEnabledDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.enable_gps))
            .setMessage(getString(R.string.required_for_this_app))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.enable_now)) { _, _ ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .show()
    }


    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onDestroyView() {
        binding.weatherPager.adapter = null
        locationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
        locationCallback = null
        super.onDestroyView()
        _binding = null
    }

}