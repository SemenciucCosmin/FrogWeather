package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.DISTANCE_KM
import com.example.frogweather.data.utils.DISTANCE_MI
import com.example.frogweather.data.utils.KM_TO_MI
import kotlin.math.roundToInt

enum class DistanceType(val distanceType: String, val labelResourceId: Int, val valueResourceId: Int) {
    KILOMETERS(DISTANCE_KM, R.string.lbl_km, R.string.lbl_visibility_value_km),
    MILES(DISTANCE_MI, R.string.lbl_mi, R.string.lbl_visibility_value_mi);

    companion object {
        fun getByDistanceType(distanceType: String) =
            values().firstOrNull { it.distanceType == distanceType } ?: KILOMETERS

        fun convertFromMeters(distance: Int, distanceType: String): Int {
            return when (getByDistanceType(distanceType)) {
                KILOMETERS -> {
                    distance / 1000
                }
                MILES -> {
                    (distance / 1000 * KM_TO_MI).roundToInt()
                }
            }
        }
    }
}
