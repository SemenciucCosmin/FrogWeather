package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class ForecastDaily(
    @Json(name = "dt")
    val dt: Long = 0,
    @Json(name = "sunrise")
    val sunrise: Long = 0,
    @Json(name = "sunset")
    val sunset: Long = 0,
    @Json(name = "temp")
    val temperature: Temperature,
    @Json(name = "feels_like")
    val feelsLike: FeelsLike,
    @Json(name = "pressure")
    val pressure: Int = 0,
    @Json(name = "humidity")
    val humidity: Int = 0,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "speed")
    val speed: Double = 0.0,
    @Json(name = "deg")
    val deg: Int = 0,
    @Json(name = "gust")
    val gust: Double = 0.0,
    @Json(name = "clouds")
    val clouds: Int,
    @Json(name = "rain")
    val rain: Double = 0.0,
    @Json(name = "pop")
    val pop: Double = 0.0
)