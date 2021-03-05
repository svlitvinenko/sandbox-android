package ru.svlit.sandbox.feature.host.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.kodein.di.*
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.core.base.entry.EntryPoint
import ru.svlit.sandbox.feature.host.presentation.HostViewModel

/**
 * Зависимости разводного экрана.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
@FlowPreview
@ExperimentalCoroutinesApi
object HostDependencies : Dependencies {
    override val module: DI.Module = DI.Module(javaClass.simpleName) {
        bind<() -> HostViewModel>() with singleton { { HostViewModel(instance()) } }
        bind() from setBinding<EntryPoint<*>>()
    }
}