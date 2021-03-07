package ru.svlit.sandbox.feature.weather.models.data

import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

/**
 * Модель ответа NBA для Погоды.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
data class WeatherNbaData(
    val value: Int,
    val location: String
) : NbaFeatureData
