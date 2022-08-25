package com.example.frogweather.data

import java.io.IOException

class NetworkRepository {
    suspend fun getDailyForecast(latitude: Double, longitude: Double, millis: Long) =
        safeApiCall(call = { getDailyForecastCall(latitude, longitude, millis) }, errorMessage = "Exception occurred")

    suspend fun getHourlyForecast(latitude: Double, longitude: Double) =
        safeApiCall(call = { getHourlyForecastCall(latitude, longitude) }, errorMessage = "Exception occurred")

    private suspend fun getDailyForecastCall(latitude: Double, longitude: Double, millis: Long): CallResult<Forecast> {
        val response = FrogWeatherApi.dailyForecastRetrofitService.getDailyForecast(latitude, longitude, millis)
        return if (!response.isSuccessful) {
            CallResult.Error(Exception(response.errorBody().toString()))
        } else {
            if (response.body() == null) {
                CallResult.Error(Exception("Response body cannot be null."))
            } else {
                CallResult.Success(response.body()!!)
            }
        }
    }

    private suspend fun getHourlyForecastCall(latitude: Double, longitude: Double): CallResult<Forecast> {
        val response = FrogWeatherApi.hourlyForecastRetrofitService.getHourlyForecast(latitude, longitude)
        return if (!response.isSuccessful) {
            CallResult.Error(Exception(response.errorBody().toString()))
        } else {
            if (response.body() == null) {
                CallResult.Error(Exception("Response body cannot be null."))
            } else {
                CallResult.Success(response.body()!!)
            }
        }
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> CallResult<T>, errorMessage: String): CallResult<T> = try {
        call.invoke()
    } catch (e: Exception) {
        CallResult.Error(IOException(errorMessage, e))
    }
}