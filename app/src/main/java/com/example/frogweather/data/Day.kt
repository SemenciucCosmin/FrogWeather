package com.example.frogweather.data

import com.squareup.moshi.Json

data class Day(
    @Json(name = "dt")
    val dt: Int = 0,
    @Json(name = "main")
    val main: Main,
    @Json(name = "weather")
    val weather: List<Weather>?,
    @Json(name = "clouds")
    val clouds: Clouds,
    @Json(name = "wind")
    val wind: Wind,
    @Json(name = "visibility")
    val visibility: Int = 0,
    @Json(name = "pop")
    val pop: Double = 0.0,
    @Json(name = "sys")
    val sys: Sys,
    @Json(name = "dt_txt")
    val dtTxt: String = ""
)