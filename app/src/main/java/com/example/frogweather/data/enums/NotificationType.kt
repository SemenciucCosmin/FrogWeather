package com.example.frogweather.data.enums

import com.example.frogweather.R
import com.example.frogweather.data.utils.NOTIFICATION_TYPE_DEFAULT
import com.example.frogweather.data.utils.NOTIFICATION_TYPE_SIMPLE

enum class NotificationType(val notificationType: String, val resourceId: Int) {
    DEFAULT(NOTIFICATION_TYPE_DEFAULT, R.string.lbl_default_android_view),
    SIMPLE(NOTIFICATION_TYPE_SIMPLE, R.string.lbl_simple_notification);

    companion object {
        fun getByNotificationType(notificationType: String) =
            values().firstOrNull { it.notificationType == notificationType } ?: SIMPLE
    }
}
