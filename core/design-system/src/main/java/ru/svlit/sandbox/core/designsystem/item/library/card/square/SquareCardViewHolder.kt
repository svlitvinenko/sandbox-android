package ru.svlit.sandbox.core.designsystem.item.library.card.square

import android.view.View
import android.widget.TextView
import kotlinx.coroutines.MainScope
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.library.button.debounce

/**
 * [ItemViewHolder], отображающий [SquareCard].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class SquareCardViewHolder(itemView: View, private val eventListener: EventListener) : ItemViewHolder<SquareCard>(itemView) {

    private val textView: TextView = itemView.findViewById(R.id.text_view)

    override fun bind(item: SquareCard) {
        textView.text = item.text.getText(itemView.context)
        itemView.setOnClickListener(debounce(coroutineScope = MainScope()) {
            eventListener.onEvent(item.onClickEvent)
        })
    }
}