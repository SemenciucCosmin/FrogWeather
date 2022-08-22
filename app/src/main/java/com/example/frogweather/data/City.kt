package com.example.frogweather.data

import com.squareup.moshi.Json

data class City(
    val id: Int = 0,
    val name: String = "",
    @Json(name = "coord")
    val coordinates: Coordinates,
    val population: Int = 0,
    val timezone: Int = 0,
    val country: String = "",
    val sunrise: Int = 0,
    val sunset: Int = 0
)