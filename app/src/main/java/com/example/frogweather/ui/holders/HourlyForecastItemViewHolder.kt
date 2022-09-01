package com.example.frogweather.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.ForecastDetail
import com.example.frogweather.databinding.OneDayHourlyForecastItemBinding

class HourlyForecastItemViewHolder(private val binding: OneDayHourlyForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(forecastDetail: ForecastDetail) {
        binding.hourlyForecastPrecipitation.text = if (forecastDetail.chance != 0) {itemView.resources.getString(R.string.lbl_hourly_forecast_precipitation).format(forecastDetail.chance)} else {""}
        binding.hourlyForecastTemperature.text = itemView.resources.getString(R.string.lbl_hourly_forecast_temperature).format(forecastDetail.temperature)
        binding.hourlyForecastTime.text = forecastDetail.time
    }
}