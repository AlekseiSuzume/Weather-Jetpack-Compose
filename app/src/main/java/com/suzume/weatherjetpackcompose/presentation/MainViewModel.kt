package com.suzume.weatherjetpackcompose.presentation

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.weatherjetpackcompose.data.repository.WeatherRepositoryImpl
import com.suzume.weatherjetpackcompose.domain.usecases.LoadWeatherUseCase
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,

    ) : AndroidViewModel(application) {

    private val repositoryImpl: WeatherRepositoryImpl = WeatherRepositoryImpl(application)
    private val loadWeatherUseCase: LoadWeatherUseCase = LoadWeatherUseCase(repositoryImpl)

    private val _searchBarState: MutableState<SearchBarState> =
        mutableStateOf(SearchBarState.CLOSED)
    val searchBarState: State<SearchBarState> = _searchBarState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    private val _weatherState: MutableState<WeatherState> = mutableStateOf(WeatherState.Progress)
    val weatherState: State<WeatherState> = _weatherState

    fun updateSearchBarState(newState: SearchBarState) {
        _searchBarState.value = newState
    }

    fun updateSearchTextState(newText: String) {
        _searchTextState.value = newText
    }

    fun loadData(city: String) {
        viewModelScope.launch {
            _weatherState.value = loadWeatherUseCase(city)
            when (weatherState.value) {
                is WeatherState.Progress -> {

                }
                is WeatherState.ResultValue -> {

                }
                is WeatherState.Error -> {

                }
            }
        }
    }

}