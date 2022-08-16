package com.example.frogweather.data

enum class WindDirectionType(val windDirectionType: Int) {
    NO_INDICATION(0),
    ARROWS(1),
    ABBREVIATIONS(2);

    companion object {
        fun getByWindDirectionType(windDirectionType: Int) =
            values().firstOrNull { it.windDirectionType == windDirectionType } ?: ARROWS
    }
}