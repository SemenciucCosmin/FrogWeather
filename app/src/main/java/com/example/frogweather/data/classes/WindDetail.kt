package com.example.frogweather.data.classes

import com.example.frogweather.data.enums.DirectionType

data class WindDetail(
    val speed: Speed,
    val directionType: DirectionType,
    val time: String
)
