package ru.svlit.sandbox.core.designsystem.item.library.button

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * [ItemViewHolderCreator], создающий [ItemViewHolder] для [ButtonItem].
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class ButtonItemViewHolderCreator : ItemViewHolderCreator<ButtonItem> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<ButtonItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, attachToRoot)
        return ButtonItemViewHolder(view, eventListener)
    }
}