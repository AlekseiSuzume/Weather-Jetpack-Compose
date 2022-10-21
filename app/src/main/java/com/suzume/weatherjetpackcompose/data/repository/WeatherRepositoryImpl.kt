package com.suzume.weatherjetpackcompose.data.repository

import android.util.Log
import com.suzume.weatherjetpackcompose.data.mappers.toWeatherEntity
import com.suzume.weatherjetpackcompose.data.network.ApiFactory
import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {

    private val api = ApiFactory.getApiService()

    override suspend fun loadWeatherUseCase() {
        val response = api.loadWeather()
        if (response.isSuccessful) {
            val weatherEntity =  response.body()?.toWeatherEntity()
//            Log.d("MyTest:WeatherRepositoryImpl", "body: ${response.body().toString()}")
            Log.d("MyTest:WeatherRepositoryImpl", "body: ${weatherEntity.toString()}")
        }
    }
}