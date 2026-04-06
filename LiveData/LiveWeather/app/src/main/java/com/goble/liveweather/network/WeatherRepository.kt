package com.goble.liveweather.network

import com.goble.liveweather.data.CurrentWeather
import com.goble.liveweather.data.DailyWeather
import com.goble.liveweather.data.WeatherData
import com.goble.liveweather.data.WeatherUnits
import org.json.JSONObject

class WeatherRepository {
    private val apiService = WeatherClient.weatherApiService

    suspend fun getCurrentWeather(longitude:Double, latitude:Double): Result<WeatherData> {
        return try {
            val json_og = apiService.getCurrentWeather(longitude = longitude, latitude = latitude)
            val root = JSONObject(json_og)
            val result = parseWeatherData(root)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(Exception("API Error: ${e.message}"))
        }
    }
}

private fun parseWeatherData(obj: JSONObject): WeatherData = WeatherData(
    longitude = obj.optString("latitude").toDouble(),
    latitude = obj.optString("latitude").toDouble(),
    current = parseCurrentWeather(obj),
    units = parseUnits(obj),
    daily = parseDaily(obj)
)

private fun parseCurrentWeather(obj: JSONObject): CurrentWeather {
    val current = obj.getJSONObject("current")
    
    return CurrentWeather(
        temperature = current.optDouble("temperature_2m"),
        feelsLike = current.optDouble("apparent_temperature"),
        weatherCode = current.optInt("weather_code"),
        precipitation = current.optDouble("precipitation")
    )
}

private fun parseUnits(obj: JSONObject): WeatherUnits = WeatherUnits(
    tempUnit = obj.optString("temperature_2m"),
    precipitationUnit = obj.optString("precipitation")
)

private fun parseDaily(obj: JSONObject): DailyWeather {
    val daily = obj.getJSONObject("daily")
    val highs = daily.getJSONArray("temperature_2m_max")
    val lows = daily.getJSONArray("temperature_2m_min")

    return DailyWeather(
        weeklyHighs = highs as List<Double>,
        weeklyLows = lows as List<Double>
    )
}