package com.goble.liveweather.data

import kotlinx.serialization.SerialName

data class WeatherData(
    val longitude: Double,
    val latitude: Double,
    val current: CurrentWeather,
    val units: WeatherUnits,
    val daily: DailyWeather
)

data class DailyWeather(
    @SerialName("temperature_2m_max")
    val weeklyHighs: List<Double>,
    @SerialName("temperature_2m_min")
    val weeklyLows:List<Double>
)

data class CurrentWeather(
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("apparent_temperature")
    val feelsLike: Double,
    val weatherCode: Int,
    val precipitation: Double
)

data class WeatherUnits(
    @SerialName("temperature_2m")
    val tempUnit: String,
    val precipitationUnit: String
)