package com.example.frogweather.ui.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frogweather.R
import com.example.frogweather.data.classes.CallResult
import com.example.frogweather.data.classes.ForecastDetail
import com.example.frogweather.data.classes.OneDayListItem
import com.example.frogweather.data.classes.PrecipitationDetail
import com.example.frogweather.data.classes.Settings
import com.example.frogweather.data.classes.TenDaysListItem
import com.example.frogweather.data.classes.WindDetail
import com.example.frogweather.data.dtos.City
import com.example.frogweather.data.dtos.ForecastDaily
import com.example.frogweather.data.dtos.ForecastHourly
import com.example.frogweather.data.enums.DayType
import com.example.frogweather.data.enums.DirectionType
import com.example.frogweather.data.enums.DistanceType
import com.example.frogweather.data.enums.LengthType
import com.example.frogweather.data.enums.PressureType
import com.example.frogweather.data.enums.SpeedType
import com.example.frogweather.data.enums.TemperatureType
import com.example.frogweather.data.repository.NetworkRepository
import com.example.frogweather.data.utils.ALL_HOURS_TIME_PATTERN
import com.example.frogweather.data.utils.AM_PM_TIME_PATTERN
import com.example.frogweather.data.utils.CONNECTION_ERROR_MESSAGE
import com.example.frogweather.data.utils.DATE_PATTERN
import com.example.frogweather.data.utils.HOUR_PATTERN
import com.example.frogweather.data.utils.LAST_FORECAST_HOUR
import com.example.frogweather.data.utils.TOMORROW_FORECAST_DAYS
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class NetworkViewModel : ViewModel() {
    private val networkRepository = NetworkRepository()
    private val _uiState = MutableLiveData<UIState>().apply {
        value = UIState(tenDaysItems = emptyList(), oneDayItems = emptyList())
    }
    val uiState = _uiState

    fun requestUiState(latitude: Double, longitude: Double, settings: Settings, dayType: Int? = 0) {
        viewModelScope.launch {
            when (val dailyResult = networkRepository.getDailyForecast(latitude, longitude)) {
                is CallResult.Success ->
                    when (
                        val hourlyResult =
                            networkRepository.getHourlyForecast(latitude, longitude)
                    ) {
                        is CallResult.Success ->
                            _uiState.value = _uiState.value?.copy(
                                tenDaysItems = getTenDaysListItems(
                                    settings,
                                    dailyResult.data.list,
                                    hourlyResult.data.list
                                ),
                                oneDayItems = getOneDayListItems(
                                    dayType,
                                    settings,
                                    dailyResult.data.list,
                                    hourlyResult.data.list
                                ),
                                city = dailyResult.data.city,
                                errorMessageTenDays = null
                            )
                        is CallResult.Error ->
                            _uiState.value =
                                _uiState.value?.copy(errorMessageTenDays = CONNECTION_ERROR_MESSAGE)
                    }
                is CallResult.Error ->
                    _uiState.value =
                        _uiState.value?.copy(errorMessageTenDays = CONNECTION_ERROR_MESSAGE)
            }
        }
    }

    private fun getOneDayListItems(
        dayType: Int?,
        settings: Settings,
        dailyForecasts: List<ForecastDaily>?,
        hourlyForecasts: List<ForecastHourly>?
    ): List<OneDayListItem> {
        if (dayType != null) {
            val forecasts = getForecastsByDayType(DayType.getByDayType(dayType), hourlyForecasts)
            if (forecasts.isNotEmpty()) {
                val currentForecast = getCurrentForecastDataClass(
                    forecasts,
                    settings,
                    DayType.getByDayType(dayType),
                    dailyForecasts
                )
                val hourlyForecast = getHourlyForecastsDataClass(forecasts, settings)
                val chance = getChanceDataClass(DayType.getByDayType(dayType), dailyForecasts)
                val details = getDetailsDataClass(forecasts.first(), settings)
                val hourlyPrecipitation = getHourlyPrecipitationsDataClass(forecasts, settings)
                val hourlyWind = getHourlyWindDataClass(forecasts, settings)
                return listOf(
                    currentForecast,
                    hourlyForecast,
                    chance,
                    details,
                    hourlyPrecipitation,
                    hourlyWind
                )
            }
        }
        return listOf()
    }

    private fun getCurrentForecastDataClass(
        forecasts: List<ForecastHourly>,
        settings: Settings,
        dayType: DayType,
        dailyForecasts: List<ForecastDaily>?
    ): OneDayListItem.CurrentForecastDataClass {
        val time = if (settings.hourFormatUnit) {
            SimpleDateFormat(
                AM_PM_TIME_PATTERN,
                Locale.ENGLISH
            ).format(Date(System.currentTimeMillis()))
        } else {
            SimpleDateFormat(
                ALL_HOURS_TIME_PATTERN,
                Locale.ENGLISH
            ).format(Date(System.currentTimeMillis()))
        }
        val minTemperature =
            TemperatureType.convertFromKelvin(
                dailyForecasts?.get(dayType.dayPosition)?.temperature?.min?.roundToInt() ?: 0,
                settings.temperatureUnit
            )
        val maxTemperature =
            TemperatureType.convertFromKelvin(
                dailyForecasts?.get(dayType.dayPosition)?.temperature?.max?.roundToInt() ?: 0,
                settings.temperatureUnit
            )
        val currentTemperature =
            TemperatureType.convertFromKelvin(
                forecasts.firstOrNull()?.measurements?.temp?.roundToInt() ?: 0,
                settings.temperatureUnit
            )
        val feelsLike =
            TemperatureType.convertFromKelvin(
                forecasts.firstOrNull()?.measurements?.feelsLike?.roundToInt() ?: 0,
                settings.temperatureUnit
            )
        return OneDayListItem.CurrentForecastDataClass(
            dayType.dateTimeResourceId,
            DayType.getDayOfWeek(dayType, System.currentTimeMillis()),
            DayType.getDayOfMonth(dayType, System.currentTimeMillis()),
            time,
            minTemperature,
            maxTemperature,
            currentTemperature,
            TemperatureType.getByTemperatureType(settings.temperatureUnit).unitId,
            feelsLike,
            forecasts.first().weather.first().description,
            dayType.dayType
        )
    }

    private fun getHourlyForecastsDataClass(
        forecasts: List<ForecastHourly>,
        settings: Settings
    ): OneDayListItem.HourlyForecastDataClass {
        val timeFormatter = SimpleDateFormat(ALL_HOURS_TIME_PATTERN, Locale.ENGLISH)
        return OneDayListItem.HourlyForecastDataClass(
            forecasts.map { forecast ->
                ForecastDetail(
                    TemperatureType.convertFromKelvin(
                        forecast.measurements.temp.roundToInt(),
                        settings.temperatureUnit
                    ),
                    (forecast.pop * 100).roundToInt(),
                    timeFormatter.format(Date(forecast.dt * 1000))
                )
            }
        )
    }

    private fun getChanceDataClass(dayType: DayType, dailyForecasts: List<ForecastDaily>?) =
        OneDayListItem.ChanceDataClass(
            ((dailyForecasts?.get(dayType.dayPosition)?.pop)?.times(100))?.roundToInt() ?: 0
        )

    private fun getDetailsDataClass(
        forecast: ForecastHourly,
        settings: Settings
    ): OneDayListItem.DetailsDataClass {
        return OneDayListItem.DetailsDataClass(
            forecast.measurements.humidity,
            PressureType.convertFromHpa(forecast.measurements.pressure, settings.pressureUnit),
            PressureType.getByPressureType(settings.pressureUnit).valueResourceId,
            DistanceType.convertFromMeters(forecast.visibility, settings.distanceUnit),
            DistanceType.getByDistanceType(settings.distanceUnit).valueResourceId
        )
    }

    private fun getHourlyPrecipitationsDataClass(
        forecasts: List<ForecastHourly>,
        settings: Settings
    ): OneDayListItem.PrecipitationDataClass {
        val timeFormatter = SimpleDateFormat(ALL_HOURS_TIME_PATTERN, Locale.ENGLISH)
        return OneDayListItem.PrecipitationDataClass(
            forecasts.map { forecast ->
                PrecipitationDetail(
                    (forecast.pop * 100).roundToInt(),
                    LengthType.convertFromMillimeters(forecast.rain.value, settings.lengthUnit),
                    timeFormatter.format(Date(forecast.dt * 1000)),
                    getPrecipitationIconResourceId(
                        LengthType.convertFromMillimeters(
                            forecast.rain.value,
                            settings.lengthUnit
                        )
                    )
                )
            },
            LengthType.getByLengthType(settings.lengthUnit).shortResourceId
        )
    }

    private fun getHourlyWindDataClass(
        forecasts: List<ForecastHourly>,
        settings: Settings
    ): OneDayListItem.WindDataClass {
        val timeFormatter = SimpleDateFormat(ALL_HOURS_TIME_PATTERN, Locale.ENGLISH)
        return OneDayListItem.WindDataClass(
            forecasts.map { forecast ->
                WindDetail(
                    SpeedType.convertSpeed(forecast.wind.speed, forecast.wind.gust, settings.speedUnit),
                    DirectionType.getByDirectionDegrees(forecast.wind.deg),
                    timeFormatter.format(Date(forecast.dt * 1000))
                )
            },
            SpeedType.getBySpeedType(settings.speedUnit).shortResourceId
        )
    }

    private fun getPrecipitationIconResourceId(volume: Double): Int {
        return when {
            volume == 0.0 -> R.drawable.ic_precipitation_level_0
            0.0 < volume && volume <= 0.5 -> R.drawable.ic_precipitation_level_1
            0.5 < volume && volume <= 1.0 -> R.drawable.ic_precipitation_level_2
            1.0 < volume && volume <= 1.5 -> R.drawable.ic_precipitation_level_3
            1.5 < volume -> R.drawable.ic_precipitation_level_4
            else -> R.drawable.ic_precipitation_level_0
        }
    }

    private fun getForecastsByDayType(
        dayType: DayType,
        forecasts: List<ForecastHourly>?
    ): List<ForecastHourly> {
        val dateFormatter = SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH)
        val hourFormatter = SimpleDateFormat(HOUR_PATTERN, Locale.ENGLISH)
        val date = dateFormatter.format(Date(System.currentTimeMillis()))
        if (forecasts != null) {
            val separatorForecastIndex = forecasts.indexOfFirst {
                dateFormatter.format(Date(it.dt * 1000)) != date && hourFormatter.format(Date(it.dt * 1000)) == LAST_FORECAST_HOUR
            }

            return if (dayType == DayType.TODAY) {
                forecasts.subList(forecasts.indexOf(forecasts.first()), separatorForecastIndex)
            } else {
                forecasts.subList(
                    separatorForecastIndex,
                    separatorForecastIndex + TOMORROW_FORECAST_DAYS
                )
            }
        }
        return listOf()
    }

    private fun getTenDaysListItems(
        settings: Settings,
        dailyForecasts: List<ForecastDaily>?,
        hourlyForecasts: List<ForecastHourly>?
    ): List<TenDaysListItem> {
        if (dailyForecasts != null && hourlyForecasts != null) {
            val timePattern = if (settings.hourFormatUnit) {
                AM_PM_TIME_PATTERN
            } else {
                ALL_HOURS_TIME_PATTERN
            }
            return dailyForecasts.map { forecast ->
                TenDaysListItem(
                    forecast != dailyForecasts.first(),
                    DayType.getDayOfWeek(DayType.TODAY, forecast.dt * 1000),
                    DayType.getDayOfMonth(DayType.TODAY, forecast.dt * 1000),
                    forecast.weather.first().description,
                    (forecast.pop * 100).roundToInt(),
                    TemperatureType.convertFromKelvin(
                        forecast.temperature.max.roundToInt(),
                        settings.temperatureUnit
                    ),
                    TemperatureType.convertFromKelvin(
                        forecast.temperature.min.roundToInt(),
                        settings.temperatureUnit
                    ),
                    LengthType.convertFromMillimeters(forecast.rain, settings.lengthUnit),
                    LengthType.getByLengthType(settings.lengthUnit).shortResourceId,
                    SpeedType.convertSpeed(forecast.speed, forecast.gust, settings.speedUnit),
                    SpeedType.getBySpeedType(settings.speedUnit),
                    DirectionType.getByDirectionDegrees(forecast.deg),
                    PressureType.convertFromHpa(forecast.pressure, settings.pressureUnit),
                    PressureType.getByPressureType(settings.pressureUnit).valueResourceId,
                    forecast.humidity,
                    SimpleDateFormat(
                        timePattern,
                        Locale.ENGLISH
                    ).format(Date(forecast.sunrise * 1000)),
                    SimpleDateFormat(
                        timePattern,
                        Locale.ENGLISH
                    ).format(Date(forecast.sunset * 1000)),
                    hourlyForecasts.filter {
                        SimpleDateFormat(
                            DATE_PATTERN,
                            Locale.ENGLISH
                        ).format(Date(it.dt * 1000)) == SimpleDateFormat(
                            DATE_PATTERN,
                            Locale.ENGLISH
                        ).format(Date(forecast.dt * 1000))
                    }.map { hourlyForecast ->
                        ForecastDetail(
                            TemperatureType.convertFromKelvin(
                                hourlyForecast.measurements.temp.roundToInt(),
                                settings.temperatureUnit
                            ),
                            0,
                            SimpleDateFormat(ALL_HOURS_TIME_PATTERN, Locale.ENGLISH).format(
                                Date(
                                    hourlyForecast.dt * 1000
                                )
                            ),
                        )
                    }
                )
            }
        }
        return listOf()
    }

    fun changeTenDaysListItemState(tenDaysListItem: TenDaysListItem) {
        val newList = _uiState.value?.tenDaysItems
        _uiState.value = _uiState.value?.copy(
            tenDaysItems = newList?.map {
                if (it == tenDaysListItem) {
                    it.copy(isCollapsed = !tenDaysListItem.isCollapsed)
                } else {
                    it.copy(isCollapsed = true)
                }
            } ?: emptyList()
        )
    }
}

data class UIState(
    val tenDaysItems: List<TenDaysListItem>,
    val oneDayItems: List<OneDayListItem>,
    val city: City? = null,
    val errorMessageTenDays: String? = null,
    val errorMessageOneDay: String? = null
)
