package com.suzume.weatherjetpackcompose.domain.model

data class ForecastEntity(
    val dayOfWeek: String,
    val parts: List<Part>,
)