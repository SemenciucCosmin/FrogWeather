package com.example.frogweather.data

import com.example.frogweather.R

enum class NotificationType(val notificationType: String, val resourceId: Int) {
    DEFAULT(NOTIFICATION_TYPE_DEFAULT, R.string.lbl_default_android_view),
    SIMPLE(NOTIFICATION_TYPE_SIMPLE, R.string.lbl_simple_notification);

    companion object {
        fun getByNotificationType(notificationType: String) =
            values().firstOrNull { it.notificationType == notificationType } ?: SIMPLE
    }
}