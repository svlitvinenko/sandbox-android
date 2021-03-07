package ru.svlit.sandbox.feature.nba.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.kodein.di.*
import ru.svlit.sandbox.core.base.di.Dependencies
import ru.svlit.sandbox.feature.nba.data.GetNbaFeaturesRepository
import ru.svlit.sandbox.feature.nba.data.NbaCreator
import ru.svlit.sandbox.feature.nba.data.api.FeaturesDeserializer
import ru.svlit.sandbox.feature.nba.data.api.GetNbaFeaturesDemoApiMapper
import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

/**
 * Зависимости разводного экрана.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
object NbaDependencies : Dependencies {
    override val module: DI.Module = DI.Module(javaClass.simpleName) {
        bind() from setBinding<NbaCreator<*>>()
        bind<Gson>("nba") with singleton {
            GsonBuilder()
                .registerTypeAdapter(NbaFeatureData::class.java, FeaturesDeserializer(instance(), instance()))
                .create()
        }
        bind<GetNbaFeaturesRepository>() with singleton {
            GetNbaFeaturesRepository(
                GetNbaFeaturesDemoApiMapper(
                    gson = instance("nba")
                )
            )
        }
    }
}