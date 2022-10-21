package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Night(
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("icon")
    val iconId: String?,
    @SerializedName("temp_avg")
    val tempAvg: Int?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)