package com.example.frogweather.data

enum class DistanceType(val distanceType: String, val index: Int) {
    KILOMETERS("km", 0),
    MILES("mi", 1);

    companion object {
        fun getByDistanceType(distanceType: String) =
            values().firstOrNull { it.distanceType == distanceType } ?: KILOMETERS
    }
}