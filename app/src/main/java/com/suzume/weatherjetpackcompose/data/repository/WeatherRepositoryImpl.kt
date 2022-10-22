package com.suzume.weatherjetpackcompose.data.repository

import android.app.Application
import android.location.Geocoder
import com.suzume.weatherjetpackcompose.data.mappers.toWeatherEntity
import com.suzume.weatherjetpackcompose.data.network.ApiService
import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository
import com.suzume.weatherjetpackcompose.domain.util.CoordinateState
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val application: Application,
    private val api: ApiService,
) : WeatherRepository {

    override suspend fun loadWeatherUseCase(lat: String, lon: String): WeatherState {
        return try {
            val response = api.loadWeather(lat = lat, lon = lon)
            val weatherEntity = response.toWeatherEntity()
            return WeatherState.ResultValue(weatherEntity)
        } catch (e: Exception) {
            when (e) {
                is java.net.UnknownHostException -> WeatherState.Error("Отсуствует подключение к интернету")
                else -> WeatherState.Error("Неизвестная ошибка")
            }
        }
    }

    override fun getCoordinateUseCase(city: String): CoordinateState {
        val geocoder = Geocoder(application, Locale.getDefault())
        return try {
            val addresses = geocoder.getFromLocationName(city, 1)
            val lat = addresses?.get(0)?.latitude.toString()
            val long = addresses?.get(0)?.longitude.toString()
            CoordinateState.Success(mutableListOf<String>().apply {
                add(lat)
                add(long)
            })
        } catch (e: Exception) {
            when (e) {
                is java.io.IOException -> CoordinateState.Error("Отсуствует подключение к интернету")
                is java.lang.IndexOutOfBoundsException -> CoordinateState.Error("Город не найден")
                else -> CoordinateState.Error("Неизвестная ошибка")
            }
        }
    }

}