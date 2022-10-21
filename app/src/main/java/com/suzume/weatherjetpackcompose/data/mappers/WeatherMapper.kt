package com.suzume.weatherjetpackcompose.data.mappers


import android.icu.text.DateFormat
import com.suzume.weatherjetpackcompose.data.network.models.WeatherDto
import com.suzume.weatherjetpackcompose.domain.model.*
import java.util.*

fun WeatherDto.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        cityName = this.geoObject?.province?.name ?: "",
        countryName = this.geoObject?.country?.name ?: "",
        currentTime = this.nowDt?.substringAfter("T")?.substringBeforeLast(":"),
        currentTemp = this.fact?.temp.toString(),
        currentIconId = this.fact?.iconId ?: "",
        currentCondition = this.fact?.condition ?: "",
        currentWindSpeed = this.fact?.windSpeed.toString(),
        forecasts = mutableMapOf<Int, Forecast>().apply {
            forecasts?.forEachIndexed { index, forecast ->
                put(index, Forecast(
                    dayOfWeek = convertTimestampToDayOfWeek(forecast?.dateTs),
                    day = Day(
                        condition = forecast?.parts?.day?.condition ?: "",
                        iconId = forecast?.parts?.day?.iconId ?: "",
                        temp = forecast?.parts?.day?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.day?.windSpeed.toString()
                    ),
                    evening = Evening(
                        condition = forecast?.parts?.evening?.condition ?: "",
                        iconId = forecast?.parts?.evening?.iconId ?: "",
                        temp = forecast?.parts?.evening?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.evening?.windSpeed.toString()
                    ),
                    morning = Morning(
                        condition = forecast?.parts?.morning?.condition ?: "",
                        iconId = forecast?.parts?.morning?.iconId ?: "",
                        temp = forecast?.parts?.morning?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.morning?.windSpeed.toString()
                    ),
                    night = Night(
                        condition = forecast?.parts?.night?.condition ?: "",
                        iconId = forecast?.parts?.night?.iconId ?: "",
                        temp = forecast?.parts?.night?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.night?.windSpeed.toString()
                    )

                ))
            }
        },
        hours = mutableListOf<Hour>().apply {
            forecasts?.get(0)?.hours?.forEach {
                add(Hour(
                    hour = it?.hour,
                    temp = it?.temp.toString(),
                    iconId = it?.iconId ?: ""
                ))
            }
        }
    )
}

private fun convertTimestampToDayOfWeek(timestamp: Int?): String {
    return timestamp?.let {
        val date = Date(timestamp * 1000L)
        val weekDay = DateFormat.getDateInstance(DateFormat.FULL).format(date)
        weekDay.substringBefore(",").replaceFirstChar { it.uppercase() }
    } ?: ""
}
