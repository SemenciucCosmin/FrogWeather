package com.example.frogweather.data

enum class PressureType(val pressureType: Int) {
    HPA(0),
    KPA(1),
    MMHG(2),
    INHG(3);

    companion object {
        fun getByPressureType(pressureType: Int) =
            values().firstOrNull { it.pressureType == pressureType } ?: MMHG
    }
}