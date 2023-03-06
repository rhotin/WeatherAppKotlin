package com.example.weatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherResponse
import com.example.weatherapp.network.Resource
import com.example.weatherapp.repository.weather.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherResponse: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    val weatherResponse: LiveData<Resource<WeatherResponse>>
        get() = _weatherResponse

    fun getWeatherByLocationRequest(location: String?) = viewModelScope.launch {
        _weatherResponse.value = repository.getWeatherByLocation(location)
    }

}