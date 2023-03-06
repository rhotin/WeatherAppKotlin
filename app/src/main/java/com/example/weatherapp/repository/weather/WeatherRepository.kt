package com.example.weatherapp.repository.weather

import com.example.weatherapp.network.api.WeatherApi
import com.example.weatherapp.repository.BaseRepository
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
) : BaseRepository() {

    suspend fun getWeatherByLocation(
        location: String? = null
    ) = safeApiCall {
        api.getWeatherByLocation(
            location ?: "4418"
        )
    }
}