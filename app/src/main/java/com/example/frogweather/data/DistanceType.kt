package com.example.frogweather.data

enum class DistanceType(val distanceType: Int) {
    KILOMETERS(0),
    MILES(1);

    companion object {
        fun getByDistanceType(distanceType: Int) =
            values().firstOrNull { it.distanceType == distanceType } ?: KILOMETERS
    }
}