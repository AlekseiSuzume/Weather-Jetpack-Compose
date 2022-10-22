package com.suzume.weatherjetpackcompose.domain.repository

import com.suzume.weatherjetpackcompose.domain.util.CoordinateState
import com.suzume.weatherjetpackcompose.domain.util.WeatherState

interface WeatherRepository {

    suspend fun loadWeatherUseCase(lat: String, lon: String): WeatherState

    fun getCoordinateUseCase(city: String): CoordinateState

}