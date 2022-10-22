package com.suzume.weatherjetpackcompose.domain.usecases

import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import javax.inject.Inject

class LoadWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend operator fun invoke(lat: String, lon: String): WeatherState {
        return repository.loadWeatherUseCase(lat, lon)
    }
}
