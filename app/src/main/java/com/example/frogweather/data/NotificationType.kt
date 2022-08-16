package com.example.frogweather.data

enum class NotificationType(val notificationType: Int) {
    DEFAULT(0),
    SIMPLE(1);

    companion object {
        fun getByNotificationType(notificationType: Int) =
            values().firstOrNull { it.notificationType == notificationType } ?: SIMPLE
    }
}