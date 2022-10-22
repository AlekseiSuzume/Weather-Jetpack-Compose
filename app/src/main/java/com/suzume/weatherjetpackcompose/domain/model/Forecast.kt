package com.suzume.weatherjetpackcompose.domain.model

data class Forecast(
    val dayOfWeek: String,
    val day: Day,
    val evening: Evening,
    val morning: Morning,
    val night: Night,
)
