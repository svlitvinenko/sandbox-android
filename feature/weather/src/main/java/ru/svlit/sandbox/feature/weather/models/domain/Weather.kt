package ru.svlit.sandbox.feature.weather.models.domain

/**
 * Текущая погода.
 *
 * @property region регион.
 * @property temperature температура по Цельсию.
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
data class Weather(
    val region: String,
    val temperature: Temperature
)
