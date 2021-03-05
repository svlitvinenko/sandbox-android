package ru.svlit.sandbox.feature.weather.data

import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData

interface CurrentWeatherLocalDataSource {

    suspend fun getCurrentWeather(): CurrentWeatherData?

    suspend fun storeCurrentWeather(data: CurrentWeatherData)
}
