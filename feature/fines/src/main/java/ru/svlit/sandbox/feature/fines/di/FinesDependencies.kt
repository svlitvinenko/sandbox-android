package ru.svlit.sandbox.feature.fines.di

import org.kodein.di.*
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.feature.fines.presentation.FinesViewModel
import ru.svlit.sandbox.feature.fines.presentation.nba.FinesNbaCreator
import ru.svlit.sandbox.feature.nba.data.NbaCreator

/**
 * Модуль с зависимостями Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
object FinesDependencies : Dependencies {

    override val module = DI.Module(name = javaClass.simpleName) {
        bind<() -> FinesViewModel>() with provider { { FinesViewModel() } }
        bind<NbaCreator<*>>().inSet() with singleton { FinesNbaCreator() }
    }
}