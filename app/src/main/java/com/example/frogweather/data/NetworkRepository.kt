package com.example.frogweather.data

class NetworkRepository {
    suspend fun getForecast(latitude: Double, longitude: Double, millis: Long): Forecast {
        return FrogWeatherApi.retrofitService.getForecast(latitude, longitude, millis, API_KEY)
    }
}