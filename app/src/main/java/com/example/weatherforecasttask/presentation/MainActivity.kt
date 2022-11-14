package com.example.weatherforecasttask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherforecasttask.App
import com.example.weatherforecasttask.R
import com.example.weatherforecasttask.databinding.ActivityMainBinding
import com.example.weatherforecasttask.di.components.ActivitySubComponent

class MainActivity : AppCompatActivity() {

    private lateinit var activitySubComponent: ActivitySubComponent

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activitySubComponent =
            (application as App).appComponent.getActivityComponentFactory().create()
        activitySubComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController



    }


    fun setToolBarTitle(title: String) {
        binding.toolbar.title = title
    }
}