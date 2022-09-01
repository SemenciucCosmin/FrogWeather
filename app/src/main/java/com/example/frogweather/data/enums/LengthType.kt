package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.LENGTH_INCHES
import com.example.frogweather.data.utils.LENGTH_MILLIMETERS
import com.example.frogweather.data.utils.MM_TO_IN

enum class LengthType(val lengthType: String, val longResourceId: Int, val shortResourceId: Int) {
    MILLIMETERS(LENGTH_MILLIMETERS, R.string.lbl_millimeters, R.string.lbl_length_unit_mm),
    INCHES(LENGTH_INCHES, R.string.lbl_inches, R.string.lbl_length_unit_mm);

    companion object {
        fun getByLengthType(lengthType: String) =
            values().firstOrNull { it.lengthType == lengthType } ?: MILLIMETERS

        fun convertFromMillimeters(volume: Double, lengthType: String): Double {
            return when (getByLengthType(lengthType)) {
                MILLIMETERS -> {
                    volume
                }
                INCHES -> {
                    volume * MM_TO_IN
                }
            }
        }
    }
}