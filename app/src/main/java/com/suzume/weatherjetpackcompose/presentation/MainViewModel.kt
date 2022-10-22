package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.weatherjetpackcompose.domain.usecases.LoadCoordinateUseCase
import com.suzume.weatherjetpackcompose.domain.usecases.LoadWeatherUseCase
import com.suzume.weatherjetpackcompose.domain.util.CoordinateState
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadWeatherUseCase: LoadWeatherUseCase,
    private val loadCoordinateUseCase: LoadCoordinateUseCase,
) : ViewModel() {

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

    fun loadCoordinate(city: String) {
        _weatherState.value = WeatherState.Progress
        when (val coordinateState = loadCoordinateUseCase(city)) {
            is CoordinateState.Success -> {
                val lat = coordinateState.coordinates[0]
                val lon = coordinateState.coordinates[1]
                loadWeather(lat, lon)
            }
            is CoordinateState.Error -> {
                _weatherState.value = WeatherState.Error(coordinateState.message)
            }
        }
    }

    private fun loadWeather(lat: String, lon: String) {
        viewModelScope.launch {
            _weatherState.value = loadWeatherUseCase(lat, lon)
        }
    }

}