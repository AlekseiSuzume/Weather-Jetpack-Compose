package com.suzume.weatherjetpackcompose.domain.util

import com.suzume.weatherjetpackcompose.domain.model.WeatherEntity

sealed class WeatherState {

    object Progress : WeatherState()
    class Error(val message: String) : WeatherState()
    class ResultValue(val weather: WeatherEntity?) : WeatherState()

}