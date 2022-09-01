package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lon")
    val lon: Double = 0.0
)