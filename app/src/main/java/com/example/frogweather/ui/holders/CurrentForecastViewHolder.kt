package com.example.frogweather.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.frogweather.R
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.data.enums.DayType
import com.example.frogweather.databinding.OneDayMainForecastBinding
import java.text.NumberFormat
import java.util.Locale

class CurrentForecastViewHolder(private val binding: OneDayMainForecastBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(currentForecast: OneDayListItem.CurrentForecastDataClass) {
        binding.mainForecastDateTime.text = itemView.resources.getString(currentForecast.dateTimeResourceId)
            .format(currentForecast.dayOfWeek, currentForecast.dayOfMonth, currentForecast.time)
        binding.mainForecastMinMaxTemperature.text = itemView.resources.getString(R.string.lbl_weather_min_max_temperature)
            .format(currentForecast.maxTemperature, currentForecast.minTemperature)

        when (DayType.getByDayType(currentForecast.dayType)) {
            DayType.TODAY -> {
                binding.apply {
                    mainForecastWeatherStateToday.text = currentForecast.description.replaceFirstChar(Char::uppercase)
                    mainForecastTemperatureUnit.text =
                        itemView.resources.getString(currentForecast.temperatureUnitResourceId).format(currentForecast.currentTemperature)
                    mainForecastCurrentTemperature.text = NumberFormat.getInstance(Locale.UK).format(currentForecast.currentTemperature)
                    mainForecastFeelsLike.text =
                        itemView.resources.getString(R.string.lbl_weather_feels_like).format(currentForecast.feelsLikeTemperature)
                    mainForecastWeatherStateTomorrow.text = ""
                }
            }
            DayType.TOMORROW -> {
                binding.apply {
                    mainForecastWeatherStateTomorrow.text = currentForecast.description.replaceFirstChar(Char::uppercase)
                    mainForecastWeatherStateToday.text = ""
                    mainForecastTemperatureUnit.text = ""
                    mainForecastCurrentTemperature.text = ""
                    mainForecastFeelsLike.text = ""
                }
            }
        }
    }
}
