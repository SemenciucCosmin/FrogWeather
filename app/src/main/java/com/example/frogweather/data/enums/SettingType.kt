package com.example.frogweather.data.enums

import com.example.frogweather.R

enum class SettingType(val resourceId: Int) {
    DETECT_LOCATION(R.string.lbl_detect_location_tab),
    HOUR_FORMAT(R.string.lbl_hour_format_tab),
    TEMPERATURE(R.string.lbl_temperature_units_tab),
    LENGTH(R.string.lbl_length_units_tab),
    SPEED(R.string.lbl_speed_units_tab),
    DISTANCE(R.string.lbl_distance_units_tab),
    PRESSURE(R.string.lbl_pressure_units_tab),
    WIND_DIRECTION_FORMAT(R.string.lbl_wind_direction_format_tab),
    SHOW_NOTIFICATION(R.string.lbl_show_notification_tab),
    NOTIFICATION(R.string.lbl_notification_type_tab);
}