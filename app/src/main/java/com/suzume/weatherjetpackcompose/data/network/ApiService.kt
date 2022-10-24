package com.suzume.weatherjetpackcompose.data.network


import com.suzume.weatherjetpackcompose.data.network.models.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    private companion object {

        const val API_KEY = "6140b52a-f61f-4a09-be4e-4fcbae1ae041"
        const val DEFAULT_LAT = "55.75396"
        const val DEFAULT_LONG = "37.620393"
        const val DEFAULT_LANG = "ru_RU"

    }

    @GET("forecast")
    suspend fun loadWeather(
        @Header("X-Yandex-API-Key") key: String = API_KEY,
        @Query("lat") lat: String = DEFAULT_LAT,
        @Query("lon") lon: String = DEFAULT_LONG,
        @Query("lang") lang: String = DEFAULT_LANG,
        @Query("hours") hours: Boolean = true,
        @Query("extra") extra: Boolean = false,
    ): WeatherDto

}