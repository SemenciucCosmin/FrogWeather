package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.MONTH_DAY_PATTERN
import com.example.frogweather.data.utils.ONE_DAY
import com.example.frogweather.data.utils.WEEK_DAY_PATTERN
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

enum class DayType(val dayType: Int, val dayPosition: Int, val dateTimeResourceId: Int) {
    TODAY(0, 0, R.string.lbl_weather_date_time_today),
    TOMORROW(1, 1, R.string.lbl_weather_date_time_tomorrow);

    companion object {
        fun getByDayType(dayType: Int) =
            values().firstOrNull { it.dayType == dayType } ?: TODAY

        fun getDayOfWeek(dayType: DayType, millis: Long): String {
            val weekDayFormatter = SimpleDateFormat(WEEK_DAY_PATTERN, Locale.ENGLISH)
            return when (dayType) {
                TODAY -> weekDayFormatter.format(Date(millis))
                TOMORROW -> weekDayFormatter.format(Date(millis + ONE_DAY))
            }
        }

        fun getDayOfMonth(dayType: DayType, millis: Long): String {
            val monthDayFormatter = SimpleDateFormat(MONTH_DAY_PATTERN, Locale.ENGLISH)
            return when (dayType) {
                TODAY -> monthDayFormatter.format(Date(millis))
                TOMORROW -> monthDayFormatter.format(Date(millis + ONE_DAY))
            }
        }
    }
}
