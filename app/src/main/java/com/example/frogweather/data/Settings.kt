package com.example.frogweather.data

data class Settings(
    val detectLocation: Boolean = false,
    val hourFormatUnit: Boolean = false,
    val temperatureUnit: String = TEMPERATURE_CELSIUS,
    val lengthUnit: String = LENGTH_MILLIMETERS,
    val speedUnit: String = SPEED_METERS,
    val distanceUnit: String = DISTANCE_KM,
    val pressureUnit: String = PRESSURE_MMHG,
    val windDirection: String = WIND_DIRECTION_ARROWS,
    val showNotification: Boolean = false,
    val notificationType: String = NOTIFICATION_TYPE_SIMPLE
)