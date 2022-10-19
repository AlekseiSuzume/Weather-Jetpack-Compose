package com.suzume.weatherjetpackcompose.data.repository

import android.util.Log
import com.suzume.weatherjetpackcompose.data.network.ApiFactory
import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {

    private val api = ApiFactory.getApiService()

    override suspend fun loadWeatherUseCase() {
        val r2 = api.loadWeather3()
        if (r2.isSuccessful){
        Log.d("MyTest:WeatherRepositoryImpl", "body: ${r2.body().toString()}")
        }
    }
}