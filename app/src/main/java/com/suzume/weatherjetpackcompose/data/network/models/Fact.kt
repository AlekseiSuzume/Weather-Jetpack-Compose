package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("icon")
    val iconId: String?,
    @SerializedName("temp")
    val temp: Int?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)