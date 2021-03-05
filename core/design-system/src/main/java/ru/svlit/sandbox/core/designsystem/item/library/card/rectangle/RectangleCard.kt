package ru.svlit.sandbox.core.designsystem.item.library.card.rectangle

import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.Item

/**
 * Прямоугольная карточка.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class RectangleCard(
    override val id: String,
    val title: String,
    val subtitle: String,
    val onClickEvent: Event
) : Item
