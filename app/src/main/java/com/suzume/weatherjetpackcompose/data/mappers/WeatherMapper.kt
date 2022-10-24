package com.suzume.weatherjetpackcompose.data.mappers


import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import com.suzume.weatherjetpackcompose.data.network.models.Forecast
import com.suzume.weatherjetpackcompose.data.network.models.WeatherDto
import com.suzume.weatherjetpackcompose.domain.model.*
import java.util.*

fun WeatherDto.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        cityName = this.geoObject?.locality?.name ?: "",
        countryName = this.geoObject?.country?.name ?: "",
        currentTime = getCurrentTime(),
        currentTemp = this.fact?.temp.toString(),
        currentIconId = this.fact?.iconId ?: "",
        currentCondition = WeatherEntity.conditions[this.fact?.condition] ?: "",
        currentWindSpeed = this.fact?.windSpeed.toString(),
        forecastEntities = mapForecast(this.forecasts),
        hours = mutableListOf<Hour>().apply {
            forecasts?.get(0)?.hours?.forEach {
                add(Hour(
                    hour = "${it?.hour}:00" ?: "",
                    temp = it?.temp.toString(),
                    iconId = it?.iconId ?: ""
                ))
            }
        }
    )
}

private fun mapForecast(forecast: List<Forecast?>?): MutableList<ForecastEntity> {
    val nightsWithoutToday = forecast?.map { it?.parts?.night }?.drop(1)
    return mutableListOf<ForecastEntity>().apply {
        forecast?.forEachIndexed { index, forecast ->
            add(ForecastEntity(
                dayOfWeek = convertTimestampToDayOfWeek(forecast?.dateTs),
                parts = mutableListOf<Part>().apply {
                    add(Morning(
                        condition = forecast?.parts?.morning?.condition ?: "",
                        iconId = forecast?.parts?.morning?.iconId ?: "",
                        temp = forecast?.parts?.morning?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.morning?.windSpeed.toString()
                    ))
                    add(Day(
                        condition = forecast?.parts?.day?.condition ?: "",
                        iconId = forecast?.parts?.day?.iconId ?: "",
                        temp = forecast?.parts?.day?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.day?.windSpeed.toString()
                    ))
                    add(Evening(
                        condition = forecast?.parts?.evening?.condition ?: "",
                        iconId = forecast?.parts?.evening?.iconId ?: "",
                        temp = forecast?.parts?.evening?.tempAvg.toString(),
                        windSpeed = forecast?.parts?.evening?.windSpeed.toString()
                    ))
                    if (nightsWithoutToday?.size!! > index) {
                        add(Night(
                            condition = nightsWithoutToday[index]?.condition ?: "",
                            iconId = nightsWithoutToday[index]?.iconId ?: "",
                            temp = nightsWithoutToday[index]?.tempAvg.toString(),
                            windSpeed = nightsWithoutToday[index]?.windSpeed.toString()
                        ))
                    }
                }
            ))
        }
    }
}


private fun convertTimestampToDayOfWeek(timestamp: Int?): String {
    return timestamp?.let {
        val date = Date(timestamp * 1000L)
        val weekDay = DateFormat.getDateInstance(DateFormat.FULL).format(date)
        weekDay.substringBefore(",").replaceFirstChar { it.uppercase() }
    } ?: ""
}

private fun getCurrentTime(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val date = Date(currentTimeMillis)
    val pattern = "HH:mm"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

