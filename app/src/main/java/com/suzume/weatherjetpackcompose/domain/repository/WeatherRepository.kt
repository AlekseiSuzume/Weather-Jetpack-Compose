package com.suzume.weatherjetpackcompose.domain.repository

interface WeatherRepository {

    suspend fun loadWeatherUseCase()

}