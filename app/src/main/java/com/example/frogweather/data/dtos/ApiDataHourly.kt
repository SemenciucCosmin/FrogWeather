package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class ApiDataHourly(
    @Json(name = "cod")
    val cod: String = "",
    @Json(name = "message")
    val message: Double = 0.0,
    @Json(name = "cnt")
    val cnt: Int = 0,
    @Json(name = "list")
    val list: List<ForecastHourly>?,
    @Json(name = "city")
    val city: City
)
