package ru.svlit.sandbox.core.designsystem.item.library.carousel

import ru.svlit.sandbox.core.designsystem.item.adapter.Item

/**
 * Элемент презентационного слоя "Карусель".
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class Carousel(
    override val id: String,
    val items: List<CarouselItem>
) : Item
