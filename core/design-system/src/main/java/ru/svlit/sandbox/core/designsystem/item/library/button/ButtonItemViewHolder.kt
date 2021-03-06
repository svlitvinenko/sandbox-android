package ru.svlit.sandbox.core.designsystem.item.library.button

import android.view.View
import android.widget.Button
import kotlinx.coroutines.MainScope
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder

/**
 * [ItemViewHolder], отображающий [ButtonItem].
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class ButtonItemViewHolder(itemView: View, private val eventListener: EventListener) : ItemViewHolder<ButtonItem>(itemView) {

    private val button: Button = itemView.findViewById(R.id.button)

    override fun bind(item: ButtonItem) {
        button.setOnClickListener(debounce(coroutineScope = MainScope()) { eventListener.onEvent(item.onClickEvent) })
        button.text = item.text.getText(itemView.context)
    }
}