package com.example.frogweather.data.classes

import com.example.frogweather.data.enums.DirectionType
import com.example.frogweather.data.enums.SpeedType

data class TenDaysListItem(
    val isCollapsed: Boolean,
    val dayOfWeek: String,
    val dayOfMonth: String,
    val forecastState: String,
    val chance: Int,
    val maxTemperature: Int,
    val minTemperature: Int,
    val volumeValue: Double,
    val volumeLabel: Int,
    val windSpeed: Speed,
    val speedType: SpeedType,
    val directionType: DirectionType,
    val pressureValue: Int,
    val pressureLabel: Int,
    val humidityValue: Int,
    val sunriseTime: String,
    val sunsetTime: String,
    val hourlyForecast: List<ForecastDetail>
)