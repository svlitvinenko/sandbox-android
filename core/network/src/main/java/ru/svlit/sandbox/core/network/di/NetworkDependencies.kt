package ru.svlit.sandbox.core.network.di

import com.google.gson.Gson
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import ru.svlit.sandbox.core.base.di.Dependencies

/**
 * Зависимости разводного экрана.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
object NetworkDependencies : Dependencies {
    override val module: DI.Module = DI.Module(javaClass.simpleName) {
        bind<Gson>() with singleton { Gson() }
    }
}