package com.example.frogweather.data

import com.squareup.moshi.Json

data class Main(
    val temp: Double = 0.0,
    @Json(name = "feels_like")
    val feelsLike: Double = 0.0,
    @Json(name = "temp_min")
    val tempMin: Double = 0.0,
    @Json(name = "temp_max")
    val tempMax: Double = 0.0,
    val pressure: Int = 0,
    @Json(name = "sea_level")
    val seaLevel: Int = 0,
    @Json(name = "grnd_level")
    val grndLevel: Int = 0,
    val humidity: Int = 0,
    @Json(name = "temp_kf")
    val tempKf: Double = 0.0
)