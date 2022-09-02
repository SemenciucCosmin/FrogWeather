package com.example.frogweather.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.ForecastDetail
import com.example.frogweather.databinding.TenDaysHourlyForecastItemBinding

class TenDaysHourlyForecastItemViewHolder(private val binding: TenDaysHourlyForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tenDaysHourlyForecast: ForecastDetail) {
        binding.apply {
            hourlyForecastTemperature.text = itemView.resources.getString(R.string.lbl_hourly_forecast_temperature).format(tenDaysHourlyForecast.temperature)
            hourlyForecastTime.text = tenDaysHourlyForecast.time
        }
    }
}
