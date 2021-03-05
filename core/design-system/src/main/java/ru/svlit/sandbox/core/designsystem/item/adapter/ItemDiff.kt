package ru.svlit.sandbox.core.designsystem.item.adapter

/**
 * Разница элементов. Устаревший и обновлённый элементы.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class ItemDiff(val oldItem: Item, val newItem: Item)
