package com.example.frogweather.data

import com.example.frogweather.R

enum class SpeedType(val speedType: String, val resourceId: Int) {
    METERS_PER_SECOND(SPEED_METERS, R.string.lbl_meters),
    KILOMETERS_PER_HOUR(SPEED_KILOMETERS, R.string.lbl_kilometers),
    MILES_PER_HOUR(SPEED_MILES, R.string.lbl_miles),
    BEAUFORT_WIND_SCALE(SPEED_BEAUFORT, R.string.lbl_beaufort),
    KNOTS(SPEED_KNOTS, R.string.lbl_knots);

    companion object {
        fun getBySpeedType(speedType: String) =
            values().firstOrNull { it.speedType == speedType } ?: METERS_PER_SECOND
    }
}