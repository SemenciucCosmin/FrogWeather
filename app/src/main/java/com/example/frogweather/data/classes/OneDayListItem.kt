package com.example.frogweather.data.classes

import com.example.frogweather.data.enums.OneDayItemViewType

sealed class OneDayListItem(val itemViewType: OneDayItemViewType) {
    data class CurrentForecastDataClass(
        val dateTimeResourceId: Int,
        val dayOfWeek: String,
        val dayOfMonth: String,
        val time: String,
        val minTemperature: Int,
        val maxTemperature: Int,
        val currentTemperature: Int,
        val temperatureUnitResourceId: Int,
        val feelsLikeTemperature: Int,
        val description: String,
        val dayType: Int
    ) : OneDayListItem(OneDayItemViewType.CURRENT_FORECAST) {
        override val id: Long
            get() = time.hashCode().toLong()
        override val content: Long
            get() = this.toString().hashCode().toLong()
    }

    data class HourlyForecastDataClass(
        val hourlyForecast: List<ForecastDetail>
    ) : OneDayListItem(OneDayItemViewType.HOURLY_FORECAST) {
        override val id: Long
            get() = hourlyForecast.size.toLong()
        override val content: Long
            get() = hourlyForecast.toString().hashCode().toLong()
    }

    data class ChanceDataClass(
        val chanceValue: Int,
    ) : OneDayListItem(OneDayItemViewType.CHANCE) {
        override val id: Long
            get() = chanceValue.toLong()
        override val content: Long
            get() = id
    }

    data class DetailsDataClass(
        val humidity: Int,
        val pressureValue: Int,
        val pressureLabel: Int,
        val visibilityValue: Int,
        val visibilityLabel: Int
    ) : OneDayListItem(OneDayItemViewType.DETAILS) {
        override val id: Long
            get() = (
                humidity.toString() +
                    pressureValue +
                    visibilityValue
                ).hashCode().toLong()
        override val content: Long
            get() = id
    }

    data class PrecipitationDataClass(
        val hourlyPrecipitation: List<PrecipitationDetail>,
        val volumeUnit: Int
    ) : OneDayListItem(OneDayItemViewType.PRECIPITATION) {
        override val id: Long
            get() = hourlyPrecipitation.size.toLong()
        override val content: Long
            get() = hourlyPrecipitation.toString().hashCode().toLong()
    }

    data class WindDataClass(
        val hourlyWind: List<WindDetail>,
        val speedUnit: Int
    ) : OneDayListItem(OneDayItemViewType.WIND) {
        override val id: Long
            get() = hourlyWind.size.toLong()
        override val content: Long
            get() = hourlyWind.toString().hashCode().toLong()
    }

    abstract val id: Long
    abstract val content: Long
}
