package ru.svlit.sandbox.core.designsystem.item.library.animated

import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Элемент "Анимированный онбординг".
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
data class AnimatedInfo(
    override val id: String,
    val title: TextWrapper,
    val subtitle: TextWrapper,
    val animation: AnimationWrapper
) : Item
