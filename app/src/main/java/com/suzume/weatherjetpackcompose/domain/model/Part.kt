package com.suzume.weatherjetpackcompose.domain.model

abstract class Part(
    open val name: String,
    open val condition: String,
    open val iconId: String,
    open val temp: String,
    open val windSpeed: String,
)