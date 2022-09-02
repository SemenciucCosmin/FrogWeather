package com.example.frogweather.data.repository

import com.example.frogweather.data.classes.CallResult
import com.example.frogweather.data.dtos.ApiDataDaily
import com.example.frogweather.data.dtos.ApiDataHourly
import com.example.frogweather.data.service.FrogWeatherApi
import java.io.IOException

class NetworkRepository {

    suspend fun getDailyForecast(latitude: Double, longitude: Double) =
        safeApiCall(call = { getDailyForecastCall(latitude, longitude) }, errorMessage = "Exception occurred")

    suspend fun getHourlyForecast(latitude: Double, longitude: Double) =
        safeApiCall(call = { getHourlyForecastCall(latitude, longitude) }, errorMessage = "Exception occurred")

    private suspend fun getDailyForecastCall(latitude: Double, longitude: Double): CallResult<ApiDataDaily> {
        val response = FrogWeatherApi.dailyForecastRetrofitService.getDailyForecast(latitude, longitude)
        return if (!response.isSuccessful) {
            CallResult.Error(Exception(response.errorBody().toString()))
        } else {
            val body = response.body()
            if (body == null) {
                CallResult.Error(Exception("Response body cannot be null."))
            } else {
                CallResult.Success(body)
            }
        }
    }

    private suspend fun getHourlyForecastCall(latitude: Double, longitude: Double): CallResult<ApiDataHourly> {
        val response = FrogWeatherApi.hourlyForecastRetrofitService.getHourlyForecast(latitude, longitude)
        return if (!response.isSuccessful) {
            CallResult.Error(Exception(response.errorBody().toString()))
        } else {
            val body = response.body()
            if (body == null) {
                CallResult.Error(Exception("Response body cannot be null."))
            } else {
                CallResult.Success(body)
            }
        }
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> CallResult<T>, errorMessage: String): CallResult<T> = try {
        call.invoke()
    } catch (e: Exception) {
        CallResult.Error(IOException(errorMessage, e))
    }
}
