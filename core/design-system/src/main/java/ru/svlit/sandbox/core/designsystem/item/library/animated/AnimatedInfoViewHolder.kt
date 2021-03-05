package ru.svlit.sandbox.core.designsystem.item.library.animated

import android.view.View
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder

/**
 * [ItemViewHolder], отображающий [AnimatedInfo].
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class AnimatedInfoViewHolder(itemView: View) : ItemViewHolder<AnimatedInfo>(itemView) {

    private val animatedView: AnimatedInfoView = itemView as AnimatedInfoView

    override fun bind(item: AnimatedInfo) {
        animatedView.setItem(item)
    }
}