package ru.svlit.sandbox.core.designsystem.item.library.info

import android.view.View
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder

/**
 * [ItemViewHolder], отображающий [Info].
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class InfoViewHolder(itemView: View) : ItemViewHolder<Info>(itemView) {

    private val infoView: InfoView = itemView as InfoView

    override fun bind(item: Info) {
        infoView.setTitle(item.title)
        infoView.setSubtitle(item.subtitle)
    }
}