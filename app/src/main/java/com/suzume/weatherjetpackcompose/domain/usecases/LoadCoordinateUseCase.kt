package com.suzume.weatherjetpackcompose.domain.usecases

import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository
import com.suzume.weatherjetpackcompose.domain.util.CoordinateState
import javax.inject.Inject

class LoadCoordinateUseCase @Inject constructor(private val repository: WeatherRepository) {

    operator fun invoke(city: String): CoordinateState {
        return repository.getCoordinateUseCase(city)
    }

}