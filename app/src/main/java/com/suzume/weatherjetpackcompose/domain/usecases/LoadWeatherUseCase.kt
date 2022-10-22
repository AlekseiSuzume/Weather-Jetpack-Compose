package com.suzume.weatherjetpackcompose.domain.usecases

import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository
import com.suzume.weatherjetpackcompose.domain.util.WeatherState

class LoadWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(city: String): WeatherState {
        return repository.loadWeatherUseCase(city)
    }
}
