package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.suzume.weatherjetpackcompose.R
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import com.suzume.weatherjetpackcompose.presentation.utils.TextWithShadow

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
                        backgroundColor = Color.White.copy(0.2f),
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
                                    MainScreenInfo(viewModel)
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
                                    WeekWeatherScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainScreenInfo(viewModel: MainViewModel) {

    val weatherState by viewModel.weatherState

    (weatherState as WeatherState.ResultValue).weather?.let { state ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                TextWithShadow(
                    text = state.cityName,
                    32.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.countryName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Последнее обновление: ${state.currentTime}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(48.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://yastatic.net/weather/i/icons/funky/dark/ovc_-ra.svg")
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextWithShadow(
                        text = state.currentCondition,
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextWithShadow(text = "${state.currentWindSpeed} м/с", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextWithShadow(
                        text = "${state.currentTemp}°",
                        fontSize = 90.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    TextWithShadow(
                        text = "C",
                        fontSize = 72.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
                Card(
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    backgroundColor = Color.White.copy(0.2f),
                    elevation = 0.dp,
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Погода на 7 дней",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}




