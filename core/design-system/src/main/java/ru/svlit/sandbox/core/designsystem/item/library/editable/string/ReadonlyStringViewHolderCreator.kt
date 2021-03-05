package ru.svlit.sandbox.core.designsystem.item.library.editable.string

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * [ItemViewHolderCreator], создающий [ItemViewHolder] для отображения [ReadonlyString].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class ReadonlyStringViewHolderCreator : ItemViewHolderCreator<ReadonlyString> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<ReadonlyString> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_readonly_string, parent, attachToRoot)
        return ReadonlyStringViewHolder(view)
    }
}
