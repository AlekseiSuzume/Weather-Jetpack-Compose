package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("date")
    val date: String?,
    @SerializedName("date_ts")
    val dateTs: Int?,
    @SerializedName("parts")
    val parts: Parts?,
    @SerializedName("sunrise")
    val sunrise: String?,
    @SerializedName("sunset")
    val sunset: String?,
)