package ru.svlit.sandbox.core.designsystem.item.library.card.square

import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.library.carousel.CarouselItem
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Квадратная карточка.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class SquareCard(
    override val id: String,
    val text: TextWrapper,
    val onClickEvent: Event
) : CarouselItem
