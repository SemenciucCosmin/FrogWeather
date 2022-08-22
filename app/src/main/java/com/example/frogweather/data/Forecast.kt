package com.example.frogweather.data

data class Forecast(
    val cod: String = "",
    val message: Int = 0,
    val cnt: Int = 0,
    val list: List<Day>?,
    val city: City
)