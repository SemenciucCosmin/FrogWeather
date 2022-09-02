package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class ForecastHourly(
    @Json(name = "dt")
    val dt: Long = 0,
    @Json(name = "main")
    val measurements: Measurements,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "clouds")
    val clouds: Clouds,
    @Json(name = "wind")
    val wind: Wind,
    @Json(name = "rain")
    val rain: Rain = Rain(),
    @Json(name = "visibility")
    val visibility: Int = 0,
    @Json(name = "pop")
    val pop: Double = 0.0,
    @Json(name = "sys")
    val sys: Sys,
    @Json(name = "dt_txt")
    val dtTxt: String = ""
)
