package ru.svlit.sandbox.core.designsystem.item.library.card.rectangle

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * [ItemViewHolderCreator], создающий [ItemViewHolder] для [RectangleCard].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class RectangleCardViewHolderCreator : ItemViewHolderCreator<RectangleCard> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<RectangleCard> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rectangle_card, parent, attachToRoot)
        return RectangleCardViewHolder(view, eventListener)
    }
}