package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.HPA_TO_INHG
import com.example.frogweather.data.utils.HPA_TO_MMHG
import com.example.frogweather.data.utils.PRESSURE_HPA
import com.example.frogweather.data.utils.PRESSURE_INHG
import com.example.frogweather.data.utils.PRESSURE_KPA
import com.example.frogweather.data.utils.PRESSURE_MMHG
import kotlin.math.roundToInt

enum class PressureType(val pressureType: String, val labelResourceId: Int, val valueResourceId: Int) {
    HPA(PRESSURE_HPA, R.string.lbl_hpa, R.string.lbl_pressure_value_hpa),
    KPA(PRESSURE_KPA, R.string.lbl_kpa, R.string.lbl_pressure_value_kpa),
    MMHG(PRESSURE_MMHG, R.string.lbl_mm_hg, R.string.lbl_pressure_value_mmhg),
    INHG(PRESSURE_INHG, R.string.lbl_in_hg, R.string.lbl_pressure_value_inhg);

    companion object {
        fun getByPressureType(pressureType: String) =
            values().firstOrNull { it.pressureType == pressureType } ?: MMHG

        fun convertFromHpa(pressure: Int, pressureType: String): Int {
            return when (getByPressureType(pressureType)) {
                HPA -> {
                    pressure
                }
                KPA -> {
                    pressure / 10
                }
                MMHG -> {
                    (pressure * HPA_TO_MMHG).roundToInt()
                }
                INHG -> {
                    (pressure * HPA_TO_INHG).roundToInt()
                }
            }
        }
    }
}
