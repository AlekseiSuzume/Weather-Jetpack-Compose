package com.suzume.weatherjetpackcompose.data.network


import com.suzume.weatherjetpackcompose.data.network.models.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    companion object {

        private const val API_KEY = "6140b52a-f61f-4a09-be4e-4fcbae1ae041"

    }

    @GET("forecast")
    suspend fun loadWeather3(
        @Header("X-Yandex-API-Key") key: String = API_KEY,
        @Query("lat") lat: String = "55.75396",
        @Query("lon") lon: String = "37.620393",
        @Query("lang") lang: String = "ru_RU",
        @Query("hours") hours: Boolean = false,
        @Query("extra") extra: Boolean = false,
    ): Response<WeatherDto>

}