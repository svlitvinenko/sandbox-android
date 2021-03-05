package ru.svlit.sandbox.core.designsystem.item.library.info

import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Информационный блок.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class Info(
    override val id: String,
    val title: TextWrapper,
    val subtitle: TextWrapper
) : Item
