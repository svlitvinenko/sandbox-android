package ru.svlit.sandbox.core.designsystem.item.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * [ViewHolder], отображающий [Item] типа [I].
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
abstract class ItemViewHolder<I : Item>(itemView: View) : ViewHolder(itemView) {

    open fun bind(item: I) {}

    open fun update(oldItem: I, newItem: I) {
        bind(newItem)
    }
}
