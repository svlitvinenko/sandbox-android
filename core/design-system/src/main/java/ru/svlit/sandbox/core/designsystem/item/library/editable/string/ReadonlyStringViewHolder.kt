package ru.svlit.sandbox.core.designsystem.item.library.editable.string

import android.view.View
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder

/**
 * [ItemViewHolder], отрисовывающий [ReadonlyString].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class ReadonlyStringViewHolder(itemView: View) : ItemViewHolder<ReadonlyString>(itemView) {

    private val readonlyStringView: ReadonlyStringView = itemView as ReadonlyStringView

    override fun bind(item: ReadonlyString) {
        readonlyStringView.setTitle(item.title, item.titleStyleType)
        readonlyStringView.setBody(item.body, item.bodyStyleType)
    }


    override fun update(oldItem: ReadonlyString, newItem: ReadonlyString) {
        if (oldItem.title != newItem.title || oldItem.titleStyleType != newItem.titleStyleType) {
            readonlyStringView.setTitle(newItem.title, newItem.titleStyleType)
        }

        if (oldItem.body != newItem.body || oldItem.bodyStyleType != newItem.bodyStyleType) {
            readonlyStringView.setBody(newItem.body, newItem.bodyStyleType)
        }
    }
}
