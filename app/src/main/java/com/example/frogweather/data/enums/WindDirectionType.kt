package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.WIND_DIRECTION_ABBREVIATIONS
import com.example.frogweather.data.utils.WIND_DIRECTION_ARROWS
import com.example.frogweather.data.utils.WIND_DIRECTION_NO_INDICATION

enum class WindDirectionType(val windDirectionType: String, val resourceId: Int) {
    NO_INDICATION(WIND_DIRECTION_NO_INDICATION, R.string.lbl_no_indications),
    ARROWS(WIND_DIRECTION_ARROWS, R.string.lbl_arrows),
    ABBREVIATIONS(WIND_DIRECTION_ABBREVIATIONS, R.string.lbl_abbreviations);

    companion object {
        fun getByWindDirectionType(windDirectionType: String) =
            values().firstOrNull { it.windDirectionType == windDirectionType } ?: ARROWS
    }
}