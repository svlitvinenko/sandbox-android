package ru.svlit.sandbox.core.designsystem.item.adapter

/**
 * Элемент презентационного слоя.
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
interface Item {

    val id: String

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}