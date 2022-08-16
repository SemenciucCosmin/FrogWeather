package com.example.frogweather.data

enum class SpeedType(val speedType: String, val index: Int) {
    METERS_PER_SECOND("Meters per second (m/s)", 0),
    KILOMETERS_PER_HOUR("Kilometers per hour (kph)", 1),
    MILES_PER_HOUR("Miles per hour (mph)", 2),
    BEAUFORT_WIND_SCALE("Beaufort wind scale (bft)", 3),
    KNOTS("Knots (kn)", 4);

    companion object {
        fun getBySpeedType(speedType: String) =
            values().firstOrNull { it.speedType == speedType } ?: METERS_PER_SECOND
    }
}