package com.suzume.weatherjetpackcompose.domain.usecases

import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository

class LoadWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke() = repository.loadWeatherUseCase()

}
