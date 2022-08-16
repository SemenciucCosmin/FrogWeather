package com.example.frogweather.data

enum class LengthType(val lengthType: Int) {
    MILLIMETERS(0),
    INCHES(1);

    companion object {
        fun getByLengthType(lengthType: Int) =
            values().firstOrNull { it.lengthType == lengthType } ?: MILLIMETERS
    }
}