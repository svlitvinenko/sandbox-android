package ru.svlit.sandbox.core.designsystem.item.adapter

/**
 * Правило связки [Item] с [ItemViewHolder].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
data class ItemRule<I : Item>(
    val itemClass: Class<I>,
    val creator: ItemViewHolderCreator<I>
)


inline fun <reified I : Item> rule(creator: ItemViewHolderCreator<I>) = ItemRule(I::class.java, creator)