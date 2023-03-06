package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.network.RemoteDataSource
import com.example.weatherapp.network.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): WeatherApi {
        return remoteDataSource.buildApi(WeatherApi::class.java, context)
    }
}