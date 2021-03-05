package ru.svlit.sandbox.core.designsystem.item.library.editable.string

import ru.svlit.sandbox.core.designsystem.item.adapter.Item
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Строка, предназначенная для чтения.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class ReadonlyString(
    override val id: String,
    val title: TextWrapper,
    val body: TextWrapper,
    val titleStyleType: TitleStyleType,
    val bodyStyleType: BodyStyleType,
) : Item {

    enum class TitleStyleType {
        HEADLINE,
        BODY_1,
        BODY_2
    }

    enum class BodyStyleType {
        PRIMARY,
        SECONDARY
    }
}
