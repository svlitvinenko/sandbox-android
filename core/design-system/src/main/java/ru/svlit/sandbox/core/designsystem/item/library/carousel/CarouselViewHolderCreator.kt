package ru.svlit.sandbox.core.designsystem.item.library.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderFactory

/**
 * [ItemViewHolderCreator], создающий [ItemViewHolder] для [Carousel].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class CarouselViewHolderCreator(private val factory: ItemViewHolderFactory) : ItemViewHolderCreator<Carousel> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<Carousel> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, attachToRoot)
        return CarouselViewHolder(view, factory, eventListener)
    }
}
