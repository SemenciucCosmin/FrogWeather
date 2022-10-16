package com.example.frogweather.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.data.classes.SavedWeatherEntry
import com.example.frogweather.databinding.SavedWeatherItemBinding

class SavedWeatherViewHolder(private val binding: SavedWeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(savedWeather: SavedWeatherEntry){
        binding.apply {
            location.text = savedWeather.location
            headerDate.text = savedWeather.date
            headerForecastState.text = savedWeather.description
            currentTemperature.text = savedWeather.current.toString()
            feelsLike.text = savedWeather.feelsLike.toString()
            headerMinTemperature.text = savedWeather.min.toString()
            headerMaxTemperature.text = savedWeather.max.toString()
        }
    }
}