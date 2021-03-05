package ru.svlit.sandbox.feature.weather.data

import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData

interface CurrentWeatherRemoteDataSource {

    suspend fun getCurrentWeather(location: String): CurrentWeatherData

}
