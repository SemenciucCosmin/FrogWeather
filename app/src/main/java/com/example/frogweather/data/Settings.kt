package com.example.frogweather.data

class Settings(
    val detectLocation: Boolean = DETECT_LOCATION_FALSE,
    val hourFormatUnit: Boolean = HOUR_FORMAT_FALSE,
    val temperatureUnit: String = TEMPERATURE_CELSIUS,
    val lengthUnit: String = LENGTH_MILLIMETERS,
    val speedUnit: String = SPEED_METERS,
    val distanceUnit: String = DISTANCE_KM,
    val pressureUnit: String = PRESSURE_MMHG,
    val windDirection: String = WIND_DIRECTION_ARROWS,
    val showNotification: Boolean = SHOW_NOTIFICATION_FALSE,
    val notificationType: String = NOTIFICATION_TYPE_SIMPLE
)