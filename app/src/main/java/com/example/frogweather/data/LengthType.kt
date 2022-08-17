package com.example.frogweather.data

import com.example.frogweather.R

enum class LengthType(val lengthType: String, val resourceId: Int) {
    MILLIMETERS(LENGTH_MILLIMETERS, R.string.lbl_millimeters),
    INCHES(LENGTH_INCHES, R.string.lbl_inches);

    companion object {
        fun getByLengthType(lengthType: String) =
            values().firstOrNull { it.lengthType == lengthType } ?: MILLIMETERS
    }
}