package com.example.frogweather.data

enum class SettingType(val settingType: String) {
    TEMPERATURE("Temperature units"),
    LENGTH("Length units"),
    SPEED("Speed units"),
    DISTANCE("Distance units"),
    PRESSURE("Pressure units"),
    WIND_DIRECTION_FORMAT("Wind direction format"),
    NOTIFICATION("Notification type");

    companion object {
        fun getBySettingType(settingType: String) =
            values().firstOrNull { it.settingType == settingType } ?: TEMPERATURE
    }
}