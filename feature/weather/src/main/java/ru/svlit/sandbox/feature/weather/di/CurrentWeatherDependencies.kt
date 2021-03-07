package ru.svlit.sandbox.feature.weather.di

import org.kodein.di.*
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderFactory
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderFactory.Companion.combinedWith
import ru.svlit.sandbox.core.designsystem.item.adapter.rule
import ru.svlit.sandbox.core.network.data.ConnectivityInterceptorImpl
import ru.svlit.sandbox.feature.nba.data.NbaCreator
import ru.svlit.sandbox.feature.weather.data.CurrentWeatherRepository
import ru.svlit.sandbox.feature.weather.data.local.CurrentWeatherLocalDataSourceImpl
import ru.svlit.sandbox.feature.weather.data.local.ForecastDatabase
import ru.svlit.sandbox.feature.weather.data.remote.CurrentWeatherApiMapper
import ru.svlit.sandbox.feature.weather.data.remote.CurrentWeatherRemoteDataSourceImpl
import ru.svlit.sandbox.feature.weather.models.presentation.WeatherExpanded
import ru.svlit.sandbox.feature.weather.presentation.ForecastViewModel
import ru.svlit.sandbox.feature.weather.presentation.item.WeatherExpandedViewHolderCreator
import ru.svlit.sandbox.feature.weather.presentation.nba.WeatherNbaCreator

/**
 * Зависимости модуля погоды.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
object CurrentWeatherDependencies : Dependencies {
    override val module: DI.Module = DI.Module(javaClass.simpleName) {
        bind<CurrentWeatherApiMapper>() with singleton {
            CurrentWeatherApiMapper(ConnectivityInterceptorImpl(instance()))
        }
        bind<CurrentWeatherRepository>() with singleton {
            CurrentWeatherRepository(
                localDataSource = CurrentWeatherLocalDataSourceImpl(ForecastDatabase(instance()).currentWeatherDao()),
                remoteDataSource = CurrentWeatherRemoteDataSourceImpl(instance())
            )
        }
        bind<() -> ForecastViewModel>() with provider {
            { ForecastViewModel(instance()) }
        }

        bind<ItemViewHolderFactory>(tag = CurrentWeatherDependencies.javaClass) with singleton {
            val baseItemViewHolderFactory: ItemViewHolderFactory = instance()
            listOf(rule(WeatherExpandedViewHolderCreator())) combinedWith baseItemViewHolderFactory
        }
        bind<NbaCreator<*>>().inSet() with singleton { WeatherNbaCreator() }

        bind<ItemViewHolderFactory>("forecast") with singleton {
            val defaultFactory: ItemViewHolderFactory = instance()
            defaultFactory + listOf(
                rule(WeatherExpandedViewHolderCreator())
            )
        }
    }
}