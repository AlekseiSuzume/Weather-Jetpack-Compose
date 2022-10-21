package com.suzume.weatherjetpackcompose.data.network.models

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("hour")
    val hour: String?,
    @SerializedName("temp")
    val temp: Int?,
    @SerializedName("icon")
    val iconId: String?,
)