package com.example.frogweather.data.classes

data class PrecipitationDetail(
    val chance: Int,
    val volume: Double,
    val time: String,
    val iconResourceId: Int
)
