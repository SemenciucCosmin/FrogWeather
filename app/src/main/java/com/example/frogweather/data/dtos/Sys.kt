package com.example.frogweather.data.dtos

import com.squareup.moshi.Json

data class Sys(
    @Json(name = "pod")
    val pod: String = ""
)