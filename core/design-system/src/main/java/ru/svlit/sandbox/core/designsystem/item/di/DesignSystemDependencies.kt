package ru.svlit.sandbox.core.designsystem.item.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderFactory
import ru.svlit.sandbox.core.designsystem.item.adapter.rule
import ru.svlit.sandbox.core.designsystem.item.library.animated.AnimatedInfoViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.button.ButtonItemViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.card.rectangle.RectangleCardViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.card.square.SquareCardViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.carousel.CarouselViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.editable.string.ReadonlyStringViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.info.InfoViewHolderCreator
import ru.svlit.sandbox.core.designsystem.item.library.readonly.string.EditableStringViewHolderCreator

/**
 * Модуль с зависимостями дизайн-системы.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
object DesignSystemDependencies : Dependencies {

    override val module = DI.Module(javaClass.simpleName) {
        bind<ItemViewHolderFactory>() with singleton {
            ItemViewHolderFactory(
                rules = listOf(
                    rule(ReadonlyStringViewHolderCreator()),
                    rule(EditableStringViewHolderCreator()),
                    rule(ButtonItemViewHolderCreator()),
                    rule(InfoViewHolderCreator()),
                    rule(AnimatedInfoViewHolderCreator()),
                    rule(
                        CarouselViewHolderCreator(
                            ItemViewHolderFactory(
                                rules = listOf(
                                    rule(SquareCardViewHolderCreator()),
                                    rule(RectangleCardViewHolderCreator()),
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}