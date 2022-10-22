package com.suzume.weatherjetpackcompose.data.repository

import android.app.Application
import android.location.Geocoder
import com.suzume.weatherjetpackcompose.data.mappers.toWeatherEntity
import com.suzume.weatherjetpackcompose.data.network.ApiFactory
import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import java.util.*

class WeatherRepositoryImpl(private val application: Application) : WeatherRepository {

    private val api = ApiFactory.getApiService()

    override suspend fun loadWeatherUseCase(city: String): WeatherState {
        val coordinates = getCoordinatesFromCityName(city)
        val response = api.loadWeather(lat = coordinates[0], lon = coordinates[1])
        return if (response.isSuccessful) {
            val weatherEntity = response.body()?.toWeatherEntity()
            WeatherState.ResultValue(weatherEntity)
        } else {
            WeatherState.Error(response.message())
        }

    }

    private fun getCoordinatesFromCityName(city: String): List<String> {
        val geocoder = Geocoder(application, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(city, 1)
        val lat = addresses?.get(0)?.latitude.toString()
        val long = addresses?.get(0)?.longitude.toString()
        return mutableListOf<String>().apply {
            add(lat)
            add(long)
        }
    }

}