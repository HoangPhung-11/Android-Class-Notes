package com.goble.liveweather.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily:String = "temperature_2m_max,temperature_2m_min",
        @Query("current") current: String = "temperature_2m,apparent_temperature,weather_code,precipitation",
        @Query("temperature_unit") tempUnit:String = "fahrenheit"
    ): String
}