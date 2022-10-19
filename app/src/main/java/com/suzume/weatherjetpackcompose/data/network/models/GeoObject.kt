package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class GeoObject(
    @SerializedName("locality")
    val locality: Locality?,
    @SerializedName("province")
    val province: Province?
)