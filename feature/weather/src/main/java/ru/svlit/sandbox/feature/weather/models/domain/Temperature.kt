package ru.svlit.sandbox.feature.weather.models.domain

import java.math.BigDecimal

/**
 * Температура.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class Temperature(
    val value: BigDecimal,
    val metrics: WeatherMetrics
)
