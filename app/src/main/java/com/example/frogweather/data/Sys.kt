package com.example.frogweather.data

import com.squareup.moshi.Json

data class Sys(
    @Json(name = "pod")
    val pod: String = ""
)