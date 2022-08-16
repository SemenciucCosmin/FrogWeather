package com.example.frogweather.data

enum class WindDirectionType(val windDirectionType: String, val index: Int) {
    NO_INDICATION("No indication", 0),
    ARROWS("Arrows", 1),
    ABBREVIATIONS("Abbreviations", 2);

    companion object {
        fun getByWindDirectionType(windDirectionType: String) =
            values().firstOrNull { it.windDirectionType == windDirectionType } ?: ARROWS
    }
}