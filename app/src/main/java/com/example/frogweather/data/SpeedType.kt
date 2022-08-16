package com.example.frogweather.data

enum class SpeedType(val speedType: Int) {
    METERS_PER_SECOND(0),
    KILOMETERS_PER_HOUR(1),
    MILES_PER_HOUR(2),
    BEAUFORT_WIND_SCALE(3),
    KNOTS(4);

    companion object {
        fun getBySpeedType(speedType: Int) =
            values().firstOrNull { it.speedType == speedType } ?: METERS_PER_SECOND
    }
}