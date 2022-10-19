package com.suzume.weatherjetpackcompose.data.network.models


import com.google.gson.annotations.SerializedName

data class Morning(
    @SerializedName("cloudness")
    val cloudness: Int?,
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("feels_like")
    val feelsLike: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("prec_strength")
    val precStrength: Double?,
    @SerializedName("prec_type")
    val precType: Int?,
    @SerializedName("pressure_mm")
    val pressureMm: Int?,
    @SerializedName("temp_avg")
    val tempAvg: Int?,
    @SerializedName("temp_max")
    val tempMax: Int?,
    @SerializedName("temp_min")
    val tempMin: Int?,
    @SerializedName("wind_dir")
    val windDir: String?,
    @SerializedName("wind_speed")
    val windSpeed: Double?
)