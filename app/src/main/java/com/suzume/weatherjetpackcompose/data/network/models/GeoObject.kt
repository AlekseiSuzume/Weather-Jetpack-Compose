package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class GeoObject(
    @SerializedName("country")
    val country: Country?,
    @SerializedName("locality")
    val locality: Locality?
)