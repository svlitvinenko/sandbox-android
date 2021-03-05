package ru.svlit.sandbox.feature.weather.data

import ru.svlit.sandbox.feature.weather.models.data.CurrentWeatherData
import ru.svlit.sandbox.feature.weather.models.domain.Temperature
import ru.svlit.sandbox.feature.weather.models.domain.Weather
import ru.svlit.sandbox.feature.weather.models.domain.WeatherMetrics.CELSIUS
import java.math.BigDecimal

/**
 * Репозиторий погоды.
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
class CurrentWeatherRepository(
    private val localDataSource: CurrentWeatherLocalDataSource,
    private val remoteDataSource: CurrentWeatherRemoteDataSource,
) {

    suspend fun getCurrentWeather(location: String, isForce: Boolean): Weather {
        return if (isForce) {
            fetchAndCache(location)
        } else {
            (localDataSource.getCurrentWeather() ?: fetchAndCache(location))
        }.toDomain()
    }

    private suspend fun fetchAndCache(location: String): CurrentWeatherData {
        val currentWeather: CurrentWeatherData = remoteDataSource.getCurrentWeather(location)
        localDataSource.storeCurrentWeather(currentWeather)
        return currentWeather
    }

    private fun CurrentWeatherData.toDomain(): Weather {
        return Weather(
            region = location.name,
            temperature = Temperature(BigDecimal.valueOf(currentWeather.temperature.toDouble()), CELSIUS)
        )
    }
}
