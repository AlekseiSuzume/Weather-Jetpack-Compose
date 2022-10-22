package com.suzume.weatherjetpackcompose.domain.repository

import com.suzume.weatherjetpackcompose.domain.util.WeatherState

interface WeatherRepository {

    suspend fun loadWeatherUseCase(city: String): WeatherState

}