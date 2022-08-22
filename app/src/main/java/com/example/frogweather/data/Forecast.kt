package com.example.frogweather.data

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "cod")
    val cod: String = "",
    @Json(name = "message")
    val message: Int = 0,
    @Json(name = "cnt")
    val cnt: Int = 0,
    @Json(name = "list")
    val list: List<Day>?,
    @Json(name = "city")
    val city: City
)