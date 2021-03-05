package ru.svlit.sandbox.core.designsystem.item.adapter

import android.view.ViewGroup

/**
 * Создатель [ItemViewHolder] для [Item] типа [I].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
interface ItemViewHolderCreator<I : Item> {

    fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<I>
}