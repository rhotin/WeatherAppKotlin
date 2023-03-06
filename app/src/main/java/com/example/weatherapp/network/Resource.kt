package com.example.weatherapp.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        var isHandled: Boolean = false
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
