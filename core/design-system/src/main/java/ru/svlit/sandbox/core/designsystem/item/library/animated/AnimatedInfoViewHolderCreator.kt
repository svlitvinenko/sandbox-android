package ru.svlit.sandbox.core.designsystem.item.library.animated

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator

/**
 * Создатель [ItemViewHolder] для [AnimatedInfo].
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class AnimatedInfoViewHolderCreator : ItemViewHolderCreator<AnimatedInfo> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<AnimatedInfo> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animated_info_fullscreen, parent, attachToRoot)
        return AnimatedInfoViewHolder(view)
    }
}