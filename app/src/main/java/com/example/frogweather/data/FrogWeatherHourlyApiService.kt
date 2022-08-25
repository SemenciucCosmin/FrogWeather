package com.example.frogweather.data

import com.example.frogweather.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val hourlyForecastRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(HOURLY_FORECAST_API_BASE_URL)
    .build()

private val dailyForecastRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(DAILY_FORECAST_API_BASE_URL)
    .build()

interface FrogWeatherHourlyApiService {
    @GET("/data/2.5/forecast/hourly")
    suspend fun getHourlyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int = CNT_HOURLY_DAYS,
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): Response<Forecast>
}
interface FrogWeatherDailyApiService {
    @GET("/data/2.5/forecast/daily")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("dt") dt: Long,
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("units") units: String = DEFAULT_API_UNITS
    ): Response<Forecast>
}

object FrogWeatherApi {
    val hourlyForecastRetrofitService: FrogWeatherHourlyApiService by lazy { hourlyForecastRetrofit.create(FrogWeatherHourlyApiService::class.java) }
    val dailyForecastRetrofitService: FrogWeatherDailyApiService by lazy { dailyForecastRetrofit.create(FrogWeatherDailyApiService::class.java) }
}

