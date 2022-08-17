package com.example.frogweather.data

import com.example.frogweather.R

enum class PressureType(val pressureType: String, val resourceId: Int) {
    HPA(PRESSURE_HPA, R.string.lbl_hpa),
    KPA(PRESSURE_KPA, R.string.lbl_kpa),
    MMHG(PRESSURE_MMHG, R.string.lbl_mm_hg),
    INHG(PRESSURE_INHG, R.string.lbl_in_hg);

    companion object {
        fun getByPressureType(pressureType: String) =
            values().firstOrNull { it.pressureType == pressureType } ?: MMHG
    }
}