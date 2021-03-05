package ru.svlit.sandbox.core.designsystem.item.library.carousel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemAdapter
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderFactory
import ru.svlit.sandbox.core.designsystem.item.decoration.BetweenItemOffsetDecoration

/**
 * [ItemViewHolder], отрисовывающий [Carousel].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class CarouselViewHolder(
    itemView: View,
    factory: ItemViewHolderFactory,
    eventListener: EventListener
) : ItemViewHolder<Carousel>(itemView) {

    private val adapter: ItemAdapter = ItemAdapter(factory, eventListener)
    private val recyclerView: RecyclerView = itemView as RecyclerView

    init {
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(BetweenItemOffsetDecoration())
    }

    override fun bind(item: Carousel) {
        adapter.submitList(item.items)
    }
}