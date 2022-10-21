package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
)