package com.example.frogweather.data

import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    val all: Int = 0
)