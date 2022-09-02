package com.example.frogweather.data.classes

import com.example.frogweather.data.utils.DISTANCE_KM
import com.example.frogweather.data.utils.LENGTH_MILLIMETERS
import com.example.frogweather.data.utils.NOTIFICATION_TYPE_SIMPLE
import com.example.frogweather.data.utils.PRESSURE_HPA
import com.example.frogweather.data.utils.SPEED_METERS
import com.example.frogweather.data.utils.TEMPERATURE_CELSIUS
import com.example.frogweather.data.utils.WIND_DIRECTION_ARROWS

data class Settings(
    val detectLocation: Boolean = false,
    val hourFormatUnit: Boolean = true,
    val temperatureUnit: String = TEMPERATURE_CELSIUS,
    val lengthUnit: String = LENGTH_MILLIMETERS,
    val speedUnit: String = SPEED_METERS,
    val distanceUnit: String = DISTANCE_KM,
    val pressureUnit: String = PRESSURE_HPA,
    val windDirection: String = WIND_DIRECTION_ARROWS,
    val showNotification: Boolean = false,
    val notificationType: String = NOTIFICATION_TYPE_SIMPLE
)
