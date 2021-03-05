package ru.svlit.sandbox.core.designsystem.item.library.info

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * [ItemViewHolderCreator], создающий [InfoViewHolder] для [Info].
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class InfoViewHolderCreator : ItemViewHolderCreator<Info> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<Info> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_fullscreen, parent, attachToRoot)
        return InfoViewHolder(view)
    }
}
