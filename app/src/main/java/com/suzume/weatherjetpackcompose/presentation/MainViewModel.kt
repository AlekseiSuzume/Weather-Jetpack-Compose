package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.weatherjetpackcompose.data.repository.WeatherRepositoryImpl
import com.suzume.weatherjetpackcompose.domain.usecases.LoadWeatherUseCase
import kotlinx.coroutines.launch

class MainViewModel(

) : ViewModel() {

    private val repositoryImpl: WeatherRepositoryImpl = WeatherRepositoryImpl()
    private val loadWeatherUseCase: LoadWeatherUseCase = LoadWeatherUseCase(repositoryImpl)

    private val _searchBarState: MutableState<SearchBarState> =
        mutableStateOf(SearchBarState.CLOSED)
    val searchBarState: State<SearchBarState> = _searchBarState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchBarState(newState: SearchBarState) {
        _searchBarState.value = newState
    }

    fun updateSearchTextState(newText: String) {
        _searchTextState.value = newText
    }

    fun loadData() {
        viewModelScope.launch {
            loadWeatherUseCase.invoke()
        }
    }

}