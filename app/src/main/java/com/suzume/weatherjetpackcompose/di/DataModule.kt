package com.suzume.weatherjetpackcompose.di

import com.suzume.weatherjetpackcompose.data.network.ApiFactory
import com.suzume.weatherjetpackcompose.data.network.ApiService
import com.suzume.weatherjetpackcompose.data.repository.WeatherRepositoryImpl
import com.suzume.weatherjetpackcompose.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @Provides
        fun provideWeatherApi(): ApiService {
            return ApiFactory.getApiService()
        }

    }

}