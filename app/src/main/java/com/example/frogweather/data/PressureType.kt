package com.example.frogweather.data

enum class PressureType(val pressureType: String, val index: Int) {
    HPA("hPa", 0),
    KPA("kPa", 1),
    MMHG("mmHg", 2),
    INHG("inHg", 3);

    companion object {
        fun getByPressureType(pressureType: String) =
            values().firstOrNull { it.pressureType == pressureType } ?: MMHG
    }
}