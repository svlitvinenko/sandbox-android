package ru.svlit.sandbox.feature.fines.presentation

import ru.svlit.sandbox.core.designsystem.item.adapter.Item

/**
 * Контент экрана Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class FinesContent(
    val title: ru.svlit.sandbox.core.models.TextWrapper,
    val mainItems: List<Item>,
    val bottomItems: List<Item>,
)