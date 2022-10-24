package com.suzume.weatherjetpackcompose.domain.model

data class Morning(
    override val name: String = "Утро",
    override val condition: String,
    override val iconId: String,
    override val temp: String,
    override val windSpeed: String
) : Part(name, condition, iconId, temp, windSpeed)