package ru.svlit.sandbox.core.designsystem.item.adapter

import android.view.ViewGroup

/**
 * Фабрика [ItemViewHolder]'ов.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class ItemViewHolderFactory(
    private val rules: List<ItemRule<*>>,
    private val viewTypeProvider: ViewTypeProvider = ViewTypeProvider()
) {


    fun provideViewType(item: Item): Int {
        return viewTypeProvider.provideViewType(item.javaClass)
    }

    @Suppress("UNCHECKED_CAST")
    fun provideViewHolder(parent: ViewGroup, viewType: Int, eventListener: EventListener): ItemViewHolder<Item> {
        return rules.first { (itemClass, _) -> viewTypeProvider.provideViewType(itemClass) == viewType }
            .creator.create(parent, false, eventListener) as ItemViewHolder<Item>
    }

    companion object {


        /**
         * Создаёт новую фабрику, наполненную правилами [additionalRules] и затем всеми правилами текущей фабрики.
         */
        operator fun ItemViewHolderFactory.plus(additionalRules: List<ItemRule<*>>): ItemViewHolderFactory {
            return ItemViewHolderFactory(additionalRules + rules)
        }

        /**
         * Создаёт новую фабрику, наполненную правилами [this] и затем всеми правилами фабрики [factory].
         */
        infix fun List<ItemRule<*>>.combinedWith(factory: ItemViewHolderFactory): ItemViewHolderFactory {
            return factory + this.toList()
        }
    }

    class ViewTypeProvider {

        private var viewTypes: MutableList<Class<*>> = mutableListOf()

        fun <I : Item> provideViewType(type: Class<I>): Int {
            val index: Int = viewTypes.indexOf(type)
            if (index != -1) return index + 1
            viewTypes.add(type)
            return provideViewType(type)
        }
    }
}
