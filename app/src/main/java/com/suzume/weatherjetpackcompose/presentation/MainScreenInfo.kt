package com.suzume.weatherjetpackcompose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suzume.weatherjetpackcompose.domain.util.WeatherState
import com.suzume.weatherjetpackcompose.presentation.utils.SvgIconLoad
import com.suzume.weatherjetpackcompose.presentation.utils.TextWithShadow

@Composable
fun MainScreenInfo(weatherState: WeatherState) {

    if (weatherState is WeatherState.ResultValue) {
        weatherState.weather?.let { state ->
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
                        SvgIconLoad(modifier = Modifier.size(48.dp), imageId = state.currentIconId)
                        Spacer(modifier = Modifier.width(8.dp))
                        TextWithShadow(
                            text = state.currentCondition,
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
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
                        backgroundColor = Color.Gray.copy(0.5f),
                        elevation = 0.dp,
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Прогноз на 7 дней",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}