package ru.svlit.sandbox.core.designsystem.item.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * [ItemCallback], обнаруживающий изменения в элементах [Item].
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
class ItemCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Item, newItem: Item): Any {
        return ItemDiff(oldItem, newItem)
    }
}
