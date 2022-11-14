package com.example.weatherforecasttask.presentation.weatherDetailsTabDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecasttask.R
import com.example.weatherforecasttask.common.Constants
import com.example.weatherforecasttask.common.DateTimeConversion
import com.example.weatherforecasttask.data.local.entities.HourlyEntity
import com.example.weatherforecasttask.databinding.ItemWeatherDetailsBinding


class HourlyWeatherAdapter :
    ListAdapter<HourlyEntity, HourlyWeatherAdapter.HourlyViewHolder>(HourlyDiffUtil()) {

    class HourlyViewHolder(private val binding: ItemWeatherDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hourly: HourlyEntity) {
            binding.tvTemp.text = binding.root.context.getString(R.string.temp, hourly.temp)
            binding.tvTime.text =
                DateTimeConversion.convertToDateTime(hourly.dt, hourly.timezoneOffSet)
            binding.tvWeatherDescription.text = binding.root.context.getString(
                R.string.current_weather_status,
                hourly.windSpeed.toString(),
                hourly.pressure,
                hourly.humidity,
            )
            Glide.with(binding.root.context)
                .load(String.format(Constants.WEATHER_ICON_URL, hourly.weather?.icon))
                .centerCrop()
                .into(binding.ivWeatherIcon)

            binding.ivWeatherIcon.contentDescription = binding.root.context
                .getString(R.string.weather_image_description, hourly.weather?.description)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val binding =
            ItemWeatherDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class HourlyDiffUtil : DiffUtil.ItemCallback<HourlyEntity>() {

    override fun areItemsTheSame(oldItem: HourlyEntity, newItem: HourlyEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HourlyEntity, newItem: HourlyEntity): Boolean {
        return oldItem == newItem
    }
}