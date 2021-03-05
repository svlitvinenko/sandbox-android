package ru.svlit.sandbox.core.designsystem.item.library.readonly.string

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * [ItemViewHolderCreator], создающий [EditableStringViewHolder].
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class EditableStringViewHolderCreator : ItemViewHolderCreator<EditableString> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<EditableString> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_editable_string, parent, attachToRoot)
        return EditableStringViewHolder(view, eventListener)
    }
}
