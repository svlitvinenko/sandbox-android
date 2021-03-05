package ru.svlit.sandbox.feature.weather.data.remote

import ru.svlit.sandbox.feature.weather.data.CurrentWeatherRemoteDataSource
import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData

class CurrentWeatherRemoteDataSourceImpl(
    private val apiMapper: CurrentWeatherApiMapper
) : CurrentWeatherRemoteDataSource {

    override suspend fun getCurrentWeather(location: String): CurrentWeatherData {
        return apiMapper.getCurrentWeather(location)
    }
}