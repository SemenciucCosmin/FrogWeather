package com.example.frogweather.data

import com.squareup.moshi.Json

data class Day(
    val dt: Int = 0,
    val main: Main,
    val weather: List<Weather>?,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int = 0,
    val pop: Double = 0.0,
    val sys: Sys,
    @Json(name = "dt_txt")
    val dtTxt: String = ""
)