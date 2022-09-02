package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.classes.Speed
import com.example.frogweather.data.utils.DEFAULT_SPEED_ICON_HEIGHT
import com.example.frogweather.data.utils.MPS_HEIGHT_UNIT
import com.example.frogweather.data.utils.MPS_TO_BFT
import com.example.frogweather.data.utils.MPS_TO_KMPH
import com.example.frogweather.data.utils.MPS_TO_KN
import com.example.frogweather.data.utils.MPS_TO_MPH
import com.example.frogweather.data.utils.SPEED_BEAUFORT
import com.example.frogweather.data.utils.SPEED_HEIGHT_MULTIPLIER
import com.example.frogweather.data.utils.SPEED_KILOMETERS
import com.example.frogweather.data.utils.SPEED_KNOTS
import com.example.frogweather.data.utils.SPEED_METERS
import com.example.frogweather.data.utils.SPEED_MILES
import kotlin.math.roundToInt

enum class SpeedType(val speedType: String, val longResourceId: Int, val shortResourceId: Int) {
    METERS_PER_SECOND(SPEED_METERS, R.string.lbl_meters, R.string.lbl_wind_speed_mps),
    KILOMETERS_PER_HOUR(SPEED_KILOMETERS, R.string.lbl_kilometers, R.string.lbl_wind_speed_kph),
    MILES_PER_HOUR(SPEED_MILES, R.string.lbl_miles, R.string.lbl_wind_speed_mph),
    BEAUFORT_WIND_SCALE(SPEED_BEAUFORT, R.string.lbl_beaufort, R.string.lbl_wind_speed_bft),
    KNOTS(SPEED_KNOTS, R.string.lbl_knots, R.string.lbl_wind_speed_kn);

    companion object {
        fun getBySpeedType(speedType: String) =
            values().firstOrNull { it.speedType == speedType } ?: METERS_PER_SECOND

        fun convertSpeed(speed: Double, gust: Double, speedType: String): Speed {
            return when (getBySpeedType(speedType)) {
                METERS_PER_SECOND -> {
                    val speedValue = speed.roundToInt()
                    val gustValue = gust.roundToInt()
                    val colorResourceId = if (speedValue >= 3) R.color.blue_8 else R.color.gray_4
                    Speed(speedValue, gustValue, colorResourceId, (speedValue * MPS_HEIGHT_UNIT) + DEFAULT_SPEED_ICON_HEIGHT)
                }
                KILOMETERS_PER_HOUR -> {
                    val speedValue = (speed * MPS_TO_KMPH).roundToInt()
                    val gustValue = (gust * MPS_TO_KMPH).roundToInt()
                    val colorResourceId = if (speedValue >= 11) R.color.blue_8 else R.color.gray_4
                    Speed(speedValue, gustValue, colorResourceId, (speedValue * SPEED_HEIGHT_MULTIPLIER / MPS_TO_KMPH).roundToInt() + DEFAULT_SPEED_ICON_HEIGHT)
                }
                MILES_PER_HOUR -> {
                    val speedValue = (speed * MPS_TO_MPH).roundToInt()
                    val gustValue = (gust * MPS_TO_MPH).roundToInt()
                    val colorResourceId = if (speedValue >= 7) R.color.blue_8 else R.color.gray_4
                    Speed(speedValue, gustValue, colorResourceId, (speedValue * SPEED_HEIGHT_MULTIPLIER / MPS_TO_MPH).roundToInt() + DEFAULT_SPEED_ICON_HEIGHT)
                }
                BEAUFORT_WIND_SCALE -> {
                    val speedValue = (speed * MPS_TO_BFT).roundToInt()
                    val gustValue = (gust * MPS_TO_BFT).roundToInt()
                    val colorResourceId = if (speedValue >= 2) R.color.blue_8 else R.color.gray_4
                    Speed(speedValue, gustValue, colorResourceId, (speedValue * SPEED_HEIGHT_MULTIPLIER / MPS_TO_BFT).roundToInt() + DEFAULT_SPEED_ICON_HEIGHT)
                }
                KNOTS -> {
                    val speedValue = (speed * MPS_TO_KN).roundToInt()
                    val gustValue = (gust * MPS_TO_KN).roundToInt()
                    val colorResourceId = if (speedValue >= 6) R.color.blue_8 else R.color.gray_4
                    Speed(speedValue, gustValue, colorResourceId, (speedValue * SPEED_HEIGHT_MULTIPLIER / MPS_TO_KN).roundToInt() + DEFAULT_SPEED_ICON_HEIGHT)
                }
            }
        }
    }
}
