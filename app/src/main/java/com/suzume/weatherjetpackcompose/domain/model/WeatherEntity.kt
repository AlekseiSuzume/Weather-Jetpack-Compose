package com.suzume.weatherjetpackcompose.domain.model

data class WeatherEntity(
    val cityName: String,
    val countryName: String,
    val currentTime: String,
    val currentTemp: String,
    val currentIconId: String,
    val currentCondition: String,
    val currentWindSpeed: String,
    val forecasts: Map<Int, Forecast>,
    val hours: List<Hour?>,
)