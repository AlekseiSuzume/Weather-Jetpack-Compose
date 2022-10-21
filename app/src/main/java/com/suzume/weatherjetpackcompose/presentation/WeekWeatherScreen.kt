package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun WeekWeatherScreen() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            bottomStart = 4.dp,
            bottomEnd = 4.dp,
        ),
        backgroundColor = Color.White.copy(0.2f),
        elevation = 0.dp
    ) {
        Column {
            InfoWeekDayShort()
        }
    }
}

@Composable
fun InfoWeekDayShort() {
    Box(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp,
            )
            .drawBehind {
                val strokeWidth = 2f
                val x = size.width - strokeWidth
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(x, 0f),
                    strokeWidth = strokeWidth
                )
            },
    ) {
        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Сегодня",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.background(Color.White.copy(0.0f))
            ) {
                LazyRow(
                ) {
                    item {
                        repeat(24) {
                            InfoPerHour()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoTimeOfDay()
            Spacer(modifier = Modifier.height(8.dp))
            InfoTimeOfDay()
            Spacer(modifier = Modifier.height(8.dp))
            InfoTimeOfDay()
            Spacer(modifier = Modifier.height(8.dp))
            InfoTimeOfDay()
        }
    }
}

@Composable
fun InfoTimeOfDay() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Утром",
            color = Color.White
        )
        AsyncImage(
            modifier = Modifier.size(32.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://yastatic.net/weather/i/icons/funky/dark/ovc_-ra.svg")
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null
        )
        Text(
            text = "+7°",
            color = Color.White
        )
        Text(
            text = "5,7 м/с",
            color = Color.White
        )
    }
}

@Composable
private fun InfoPerHour() {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "12 ч",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://yastatic.net/weather/i/icons/funky/dark/ovc_-ra.svg")
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "16°",
                color = Color.White
            )
        }
    }
}