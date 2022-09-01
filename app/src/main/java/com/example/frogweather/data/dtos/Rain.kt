package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class Rain(
    @Json(name = "1h")
    val value: Double = 0.0,
)