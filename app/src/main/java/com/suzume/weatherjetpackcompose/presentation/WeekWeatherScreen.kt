package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.suzume.weatherjetpackcompose.domain.model.ForecastEntity
import com.suzume.weatherjetpackcompose.domain.model.Hour
import com.suzume.weatherjetpackcompose.domain.model.Part
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import com.suzume.weatherjetpackcompose.presentation.utils.SvgIconLoad

@Composable
fun WeekWeatherScreen(weatherState: WeatherState) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            bottomStart = 4.dp,
            bottomEnd = 4.dp,
        ),
        backgroundColor = Color.Gray.copy(0.5f),
        elevation = 0.dp
    ) {
        Column {
            InfoWeekDayShort(weatherState)
        }
    }
}

@Composable
fun InfoWeekDayShort(weatherState: WeatherState) {

    if (weatherState is WeatherState.ResultValue) {
        weatherState.weather?.let { state ->
            Box(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    )
            ) {
                Column {
                    weatherState.weather.forecastEntities.forEachIndexed { index, forecast ->
                        if (index == 0) {
                            TodayInfo(forecast, state.hours)
                        } else {
                            DayInfo(forecast)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodayInfo(forecastEntity: ForecastEntity, hours: List<Hour>) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp),
        color = Color.DarkGray,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Сегодня",
        color = Color.White
    )
    Spacer(modifier = Modifier.height(8.dp))
    Box(
        modifier = Modifier.background(Color.White.copy(0.0f))
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(hours) { hour ->
                HourInfo(hour)
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp,
            )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        forecastEntity.parts.forEach {
            TimeOfDayInfo(it)
        }
    }
}

@Composable
fun DayInfo(forecastEntity: ForecastEntity) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(0.5.dp),
        color = Color.DarkGray,
    )
    Column(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp,
            )
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = forecastEntity.dayOfWeek,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        forecastEntity.parts.forEach {
            TimeOfDayInfo(it)
        }
    }
}

@Composable
fun TimeOfDayInfo(part: Part) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        {
            Text(
                text = part.name,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SvgIconLoad(modifier = Modifier.size(32.dp), imageId = part.iconId)
                Text(
                    text = "${part.temp}°",
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        {
            Text(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = "${part.windSpeed} м/с",
                color = Color.White,
            )
        }

    }
}


@Composable
private fun HourInfo(hour: Hour) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = hour.hour,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            SvgIconLoad(modifier = Modifier.size(32.dp), imageId = hour.iconId)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${hour.temp}°",
                color = Color.White
            )
        }
    }
}