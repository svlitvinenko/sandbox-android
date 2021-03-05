package ru.svlit.sandbox.feature.weather.data.local

import ru.svlit.sandbox.feature.weather.data.CurrentWeatherLocalDataSource
import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData

class CurrentWeatherLocalDataSourceImpl(
    private val currentWeatherDao: CurrentWeatherDao
) : CurrentWeatherLocalDataSource {

    override suspend fun getCurrentWeather(): CurrentWeatherData? {
        return currentWeatherDao.get()
    }

    override suspend fun storeCurrentWeather(data: CurrentWeatherData) {
        currentWeatherDao.upsert(data)
    }
}