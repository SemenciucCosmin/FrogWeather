package com.example.frogweather.data

import com.example.frogweather.R

enum class TemperatureType(val temperatureType: String, val resourceId: Int) {
    CELSIUS(TEMPERATURE_CELSIUS, R.string.lbl_celsius),
    FAHRENHEIT(TEMPERATURE_FAHRENHEIT, R.string.lbl_fahrenheit),
    KELVIN(TEMPERATURE_KELVIN, R.string.lbl_kelvin);

    companion object {
        fun getByTemperatureType(temperatureType: String) =
            values().firstOrNull { it.temperatureType == temperatureType } ?: CELSIUS
    }
}