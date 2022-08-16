package com.example.frogweather.data

enum class LengthType(val lengthType: String, val index: Int) {
    MILLIMETERS("Millimeters (mm)", 0),
    INCHES("Inches (in)", 1);

    companion object {
        fun getByLengthType(lengthType: String) =
            values().firstOrNull { it.lengthType == lengthType } ?: MILLIMETERS
    }
}