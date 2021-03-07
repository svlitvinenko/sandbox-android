package ru.svlit.sandbox

import android.app.Application
import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.core.designsystem.item.di.DesignSystemDependencies
import ru.svlit.sandbox.core.network.di.NetworkDependencies
import ru.svlit.sandbox.feature.fines.di.FinesDependencies
import ru.svlit.sandbox.feature.host.di.HostDependencies
import ru.svlit.sandbox.feature.nba.di.NbaDependencies
import ru.svlit.sandbox.feature.weather.di.CurrentWeatherDependencies

/**
 * Класс приложения.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
@FlowPreview
@ExperimentalCoroutinesApi
@Suppress("unused")
class SandboxApplication : Application(), DIAware, Dependencies {

    override val di: DI by DI.lazy {
        import(module)

        // Core
        import(NetworkDependencies.module)
        import(DesignSystemDependencies.module)

        // Feature
        import(NbaDependencies.module)
        import(HostDependencies.module)
        import(FinesDependencies.module)
        import(CurrentWeatherDependencies.module)
    }

    override val module: DI.Module = DI.Module(javaClass.simpleName) {
        bind<Context>() with singleton { this@SandboxApplication }
    }
}