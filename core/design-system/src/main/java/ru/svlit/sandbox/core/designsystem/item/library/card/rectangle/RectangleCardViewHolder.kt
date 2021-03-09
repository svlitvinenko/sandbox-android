package ru.svlit.sandbox.core.designsystem.item.library.card.rectangle

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import kotlinx.coroutines.MainScope
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.library.button.debounce

/**
 * [ItemViewHolder], отображающий [RectangleCard].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class RectangleCardViewHolder(
    itemView: View,
    private val eventListener: EventListener
) : ItemViewHolder<RectangleCard>(itemView) {

    private val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
    private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitle_text_view)

    override fun bind(item: RectangleCard) {
        val title: String = item.title.getText(itemView.context)
        titleTextView.text = title
        titleTextView.setVisibilityBy(title)

        val subtitle: String = item.subtitle.getText(itemView.context)
        subtitleTextView.text = subtitle
        subtitleTextView.setVisibilityBy(subtitle)
        itemView.setOnClickListener(debounce(coroutineScope = MainScope()) { eventListener.onEvent(item.onClickEvent) })
    }

    private fun TextView.setVisibilityBy(text: String) {
        visibility = if (text.isNotBlank()) VISIBLE else GONE
    }
}
