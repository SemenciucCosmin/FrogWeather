package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class Weather(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "main")
    val main: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "icon")
    val icon: String = ""
)