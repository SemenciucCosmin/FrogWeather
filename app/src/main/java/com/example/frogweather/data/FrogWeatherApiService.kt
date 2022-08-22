package com.example.frogweather.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(WEATHER_API_BASE_URL)
    .build()

interface FrogWeatherApiService {
    @GET("/data/2.5/forecast?units=metric")
    suspend fun getForecast(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("dt") dt: Long, @Query("appid") appid: String): Forecast
}

object FrogWeatherApi {
    val retrofitService: FrogWeatherApiService by lazy { retrofit.create(FrogWeatherApiService::class.java) }
}