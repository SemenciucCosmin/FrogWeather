package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class FeelsLike(
    @Json(name = "day")
    val day: Double = 0.0,
    @Json(name = "night")
    val night: Double = 0.0,
    @Json(name = "eve")
    val evening: Double = 0.0,
    @Json(name = "morn")
    val morning: Double = 0.0
)
