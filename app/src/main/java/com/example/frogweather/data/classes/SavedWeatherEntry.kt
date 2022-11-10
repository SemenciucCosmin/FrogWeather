package com.example.frogweather.data.classes

data class SavedWeatherEntry(
    val location: String,
    val date: String,
    val description: String,
    val current: Int,
    val feelsLike: Int,
    val min: Int,
    val max: Int
)