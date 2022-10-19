package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("cloudness")
    val cloudness: Double?,
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("feels_like")
    val feelsLike: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("obs_time")
    val obsTime: Int?,
    @SerializedName("prec_strength")
    val precStrength: Double?,
    @SerializedName("prec_type")
    val precType: Int?,
    @SerializedName("pressure_mm")
    val pressureMm: Int?,
    @SerializedName("temp")
    val temp: Int?,
    @SerializedName("uptime")
    val uptime: Int?,
    @SerializedName("wind_dir")
    val windDir: String?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)