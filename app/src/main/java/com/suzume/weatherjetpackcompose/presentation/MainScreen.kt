package com.suzume.weatherjetpackcompose.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.suzume.weatherjetpackcompose.presentation.utils.TextWithShadow

@Preview
@Composable
fun preview() {
//    MainScreen(viewModel = MainViewModel())
//    WeekWeatherScreen()
}

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val searchBarState by viewModel.searchBarState
    val searchTextState by viewModel.searchTextState

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            BaseAppBar(searchBarState = searchBarState,
                searchTextState = searchTextState,
                onTextChanged = { viewModel.updateSearchTextState(it) },
                onCloseClicked = { viewModel.updateSearchBarState(SearchBarState.CLOSED) },
                onSearchTriggered = { viewModel.updateSearchBarState(SearchBarState.OPENED) },
                onSearchClicked = {
                    Log.d("MyTag", it)
                    viewModel.updateSearchBarState(SearchBarState.CLOSED)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MainScreenInfo()
        }
    }
}

@Composable
private fun MainScreenInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TextWithShadow(
                text = "Новосибирск",
                32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Россия",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Понедельник, 24 октября 2022 г.",
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
                    text = "Rain",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextWithShadow(text = "5 м/с", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextWithShadow(
                    text = "24°",
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




