package com.example.frogweather.data

import java.io.IOException

class NetworkRepository {
    suspend fun getForecast(latitude: Double, longitude: Double, millis: Long) =
        safeApiCall(call = { getForecastCall(latitude, longitude, millis) }, errorMessage = "Exception occurred")

    private suspend fun getForecastCall(latitude: Double, longitude: Double, millis: Long): CallResult<Forecast> {
        val response = FrogWeatherApi.retrofitService.getForecast(latitude, longitude, millis)
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