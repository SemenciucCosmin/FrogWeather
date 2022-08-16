package com.example.frogweather.data

enum class TemperatureType(val temperatureType: Int) {
    CELSIUS(0),
    FAHRENHEIT(1),
    KELVIN(2);

    companion object {
        fun getByTemperatureType(temperatureType: Int) =
            values().firstOrNull { it.temperatureType == temperatureType } ?: CELSIUS
    }
}