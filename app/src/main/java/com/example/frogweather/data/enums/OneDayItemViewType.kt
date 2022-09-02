package com.example.frogweather.data.enums

enum class OneDayItemViewType(val viewType: Int) {
    CURRENT_FORECAST(0),
    HOURLY_FORECAST(1),
    CHANCE(2),
    DETAILS(3),
    PRECIPITATION(4),
    WIND(5);

    companion object {
        fun getByItemViewType(viewType: Int) =
            values().firstOrNull { it.viewType == viewType } ?: CURRENT_FORECAST
    }
}
