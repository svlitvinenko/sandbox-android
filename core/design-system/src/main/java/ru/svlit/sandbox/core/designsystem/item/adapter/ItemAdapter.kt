package ru.svlit.sandbox.core.designsystem.item.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

/**
 * Адаптер для отображения элементов презентационного слоя..
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
class ItemAdapter(
    private val factory: ItemViewHolderFactory,
    private val eventListener: EventListener
) : ListAdapter<Item, ItemViewHolder<Item>>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<Item> {
        return factory.provideViewHolder(parent, viewType, eventListener)
    }

    override fun getItemViewType(position: Int): Int {
        return factory.provideViewType(currentList[position])
    }

    override fun onBindViewHolder(holder: ItemViewHolder<Item>, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onBindViewHolder(holder: ItemViewHolder<Item>, position: Int, payloads: MutableList<Any>) {
        val immutablePayloads: List<Any> = payloads.toList()
        val payload: ItemDiff? = immutablePayloads.filterIsInstance<ItemDiff>().lastOrNull()

        if (payload != null) {
            holder.update(payload.oldItem, payload.newItem)
        } else {
            onBindViewHolder(holder, position)
        }
    }
}
