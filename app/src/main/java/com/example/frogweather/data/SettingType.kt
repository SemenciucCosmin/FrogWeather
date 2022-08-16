package com.example.frogweather.data

enum class SettingType(val settingType: Int) {
    TEMPERATURE(0),
    LENGTH(1),
    SPEED(2),
    DISTANCE(3),
    PRESSURE(4),
    WIND_DIRECTION_FORMAT(5),
    NOTIFICATION(6);

    companion object {
        fun getBySettingType(settingType: Int) =
            values().firstOrNull { it.settingType == settingType } ?: TEMPERATURE
    }
}