package ru.svlit.sandbox.feature.weather.models.presentation

import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.feature.weather.models.domain.Temperature

/**
 * Расширенное предствление погоды.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class WeatherExpanded(
    override val id: String,
    val region: String,
    val temperature: Temperature
) : Item
