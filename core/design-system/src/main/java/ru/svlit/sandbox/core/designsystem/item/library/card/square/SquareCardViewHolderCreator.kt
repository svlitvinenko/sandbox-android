package ru.svlit.sandbox.core.designsystem.item.library.card.square

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * [ItemViewHolderCreator], создающий [ItemViewHolder] для [SquareCard].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class SquareCardViewHolderCreator : ItemViewHolderCreator<SquareCard> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<SquareCard> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_square_card, parent, attachToRoot)
        return SquareCardViewHolder(view, eventListener)
    }
}