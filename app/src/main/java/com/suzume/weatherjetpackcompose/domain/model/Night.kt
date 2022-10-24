package com.suzume.weatherjetpackcompose.domain.model

data class Night(
    override val name: String = "Ночь",
    override val condition: String,
    override val iconId: String,
    override val temp: String,
    override val windSpeed: String,
) : Part(name, condition, iconId, temp, windSpeed)