package com.example.frogweather.data

enum class TemperatureType(val temperatureType: String, val index: Int) {
    CELSIUS("Celsius", 0),
    FAHRENHEIT("Fahrenheit", 1),
    KELVIN("Kelvin", 2);

    companion object {
        fun getByTemperatureType(temperatureType: String) =
            values().firstOrNull { it.temperatureType == temperatureType } ?: CELSIUS
    }
}