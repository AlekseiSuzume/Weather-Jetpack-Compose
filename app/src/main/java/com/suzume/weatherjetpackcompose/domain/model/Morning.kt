package com.suzume.weatherjetpackcompose.domain.model

data class Morning(
    val condition: String,
    val iconId: String,
    val temp: String,
    val windSpeed: String
)