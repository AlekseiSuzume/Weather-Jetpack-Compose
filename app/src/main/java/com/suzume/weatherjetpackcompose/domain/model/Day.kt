package com.suzume.weatherjetpackcompose.domain.model

data class Day(
    override val name: String = "День",
    override val condition: String,
    override val iconId: String,
    override val temp: String,
    override val windSpeed: String
) : Part(name, condition, iconId, temp, windSpeed)