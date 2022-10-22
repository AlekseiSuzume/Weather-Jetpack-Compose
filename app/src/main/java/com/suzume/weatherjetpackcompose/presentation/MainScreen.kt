package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.suzume.weatherjetpackcompose.R
import com.suzume.weatherjetpackcompose.domain.util.WeatherState

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val searchBarState by viewModel.searchBarState
    val searchTextState by viewModel.searchTextState
    val weatherState by viewModel.weatherState

    Scaffold(
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.background_default),
                contentScale = ContentScale.Crop
            )
            .statusBarsPadding()
            .navigationBarsPadding(),
        backgroundColor = Color.Transparent,
        topBar = {
            BaseAppBar(
                searchBarState = searchBarState,
                searchTextState = searchTextState,
                onTextChanged = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateSearchBarState(SearchBarState.CLOSED) },
                onSearchTriggered = { viewModel.updateSearchBarState(SearchBarState.OPENED) },
                onSearchClicked = {
                    viewModel.loadCoordinate(it)
                    viewModel.updateSearchBarState(SearchBarState.CLOSED)
                }
            )
        }
    ) { paddingValues ->
        when (weatherState) {
            is WeatherState.Progress -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is WeatherState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Card(
                        modifier = Modifier.align(Alignment.Center),
                        backgroundColor = Color.Gray.copy(0.3f),
                        elevation = 0.dp
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            text = (weatherState as WeatherState.Error).message,
                            color = Color.DarkGray
                        )
                    }
                }
            }
            is WeatherState.ResultValue -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    BoxWithConstraints(
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(this@BoxWithConstraints.maxHeight)
                                        .padding(
                                            top = 16.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 0.dp
                                        )
                                ) {
                                    MainScreenInfo(weatherState)
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 0.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 16.dp
                                        )
                                ) {
                                    WeekWeatherScreen(weatherState)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




