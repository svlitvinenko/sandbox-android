package ru.svlit.sandbox.feature.host.presentation

import ru.svlit.sandbox.core.designsystem.item.adapter.Item

/**
 * КОнтент разводного экрана.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
data class HostContent(
    val topItems: List<Item>,
    val mainItems: List<Item>,
    val bottomItems: List<Item>,
)
