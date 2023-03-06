package com.example.weatherapp.network.api

import com.example.weatherapp.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {
    //get by location
    @GET("{location_id}.json")
    suspend fun getWeatherByLocation(@Path("location_id") locationId: String): WeatherResponse

}