package com.example.frogweather.data

enum class NotificationType(val notificationType: String, val index: Int) {
    DEFAULT("Default Android view", 0),
    SIMPLE("Simple notification", 1);

    companion object {
        fun getByNotificationType(notificationType: String) =
            values().firstOrNull { it.notificationType == notificationType } ?: SIMPLE
    }
}