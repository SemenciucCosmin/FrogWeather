package com.example.frogweather.data

import com.example.frogweather.R

enum class DistanceType(val distanceType: String, val resourceId: Int) {
    KILOMETERS(DISTANCE_KM, R.string.lbl_km),
    MILES(DISTANCE_MI, R.string.lbl_mi);

    companion object {
        fun getByDistanceType(distanceType: String) =
            values().firstOrNull { it.distanceType == distanceType } ?: KILOMETERS
    }
}