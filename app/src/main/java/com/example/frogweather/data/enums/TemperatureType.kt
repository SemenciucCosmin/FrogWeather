package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.ABSOLUTE_ZERO
import com.example.frogweather.data.utils.TEMPERATURE_CELSIUS
import com.example.frogweather.data.utils.TEMPERATURE_FAHRENHEIT
import com.example.frogweather.data.utils.TEMPERATURE_KELVIN
import kotlin.math.roundToInt

enum class TemperatureType(val temperatureType: String, val nameId: Int, val unitId: Int) {
    CELSIUS(TEMPERATURE_CELSIUS, R.string.lbl_celsius, R.string.lbl_temperature_unit_celsius),
    FAHRENHEIT(TEMPERATURE_FAHRENHEIT, R.string.lbl_fahrenheit, R.string.lbl_temperature_unit_fahrenheit),
    KELVIN(TEMPERATURE_KELVIN, R.string.lbl_kelvin, R.string.lbl_temperature_unit_kelvin);

    companion object {
        fun getByTemperatureType(temperatureType: String) =
            values().firstOrNull { it.temperatureType == temperatureType } ?: CELSIUS

        fun convertFromKelvin(temperature: Int, temperatureType: String): Int {
            return when (getByTemperatureType(temperatureType)) {
                CELSIUS -> {
                    (temperature - ABSOLUTE_ZERO).roundToInt()
                }
                FAHRENHEIT -> {
                    ((((temperature - ABSOLUTE_ZERO) * 9) / 5) + 32).roundToInt()
                }
                KELVIN -> {
                    temperature
                }
            }
        }
    }
}