package ru.svlit.sandbox.core.designsystem.item.library.card.rectangle

import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.library.carousel.CarouselItem
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Прямоугольная карточка.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class RectangleCard(
    override val id: String,
    val title: TextWrapper,
    val subtitle: TextWrapper,
    val onClickEvent: Event
) : CarouselItem
