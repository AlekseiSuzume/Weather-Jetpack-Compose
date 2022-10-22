package com.suzume.weatherjetpackcompose.domain.model

data class WeatherEntity(
    val cityName: String,
    val countryName: String,
    val currentTime: String,
    val currentTemp: String,
    val currentIconId: String,
    val currentCondition: String,
    val currentWindSpeed: String,
    val forecastEntities: List<ForecastEntity>,
    val hours: List<Hour>,
) {
    companion object{
        val conditions = mutableMapOf<String, String>().apply {
            put("clear", "Ясно")
            put("partly-cloudy", "Малооблачно")
            put("cloudy", "Облачно с прояснениями")
            put("overcast", "Пасмурно")
            put("drizzle", "Морось")
            put("light-rain", "Небольшой дождь")
            put("rain", "Дождь")
            put("moderate-rain", "Умеренно сильный дождь")
            put("heavy-rain", "Сильный дождь")
            put("continuous-heavy-rain", "Длительный сильный дождь")
            put("showers", "Ливень")
            put("wet-snow", "Дождь со снегом")
            put("light-snow", "Небольшой снег")
            put("snow", "Снег")
            put("snow-showers", "Снегопад")
            put("hail", "Град")
            put("thunderstorm", "Гроза")
            put("thunderstorm-with-rain", "Дождь с грозой")
            put("thunderstorm-with-hail", "Гроза с градом")
        }
    }
}