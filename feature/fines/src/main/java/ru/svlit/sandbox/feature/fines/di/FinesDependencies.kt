package ru.svlit.sandbox.feature.fines.di

import org.kodein.di.*
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.core.base.entry.EntryPoint
import ru.svlit.sandbox.feature.fines.entry.FinesEntryPoint
import ru.svlit.sandbox.feature.fines.presentation.FinesViewModel

/**
 * Модуль с зависимостями Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
object FinesDependencies : Dependencies {

    override val module = DI.Module(name = javaClass.simpleName) {
        bind<EntryPoint<*>>().inSet() with singleton { FinesEntryPoint() }
        bind<() -> FinesViewModel>() with provider { { FinesViewModel() } }
    }
}