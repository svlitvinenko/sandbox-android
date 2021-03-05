package ru.svlit.sandbox.core.designsystem.item.library.button

import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Элемент "Кнопка".
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class ButtonItem(
    override val id: String,
    val text: TextWrapper,
    val onClickEvent: Event
) : Item
