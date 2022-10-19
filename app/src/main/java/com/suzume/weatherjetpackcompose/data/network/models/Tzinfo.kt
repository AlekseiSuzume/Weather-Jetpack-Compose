package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Tzinfo(
    @SerializedName("name")
    val name: String?,
    @SerializedName("offset")
    val offset: Int?
)