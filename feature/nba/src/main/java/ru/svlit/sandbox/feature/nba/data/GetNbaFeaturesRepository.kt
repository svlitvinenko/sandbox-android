package ru.svlit.sandbox.feature.nba.data

import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

/**
 * Репозиторий NBA.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
class GetNbaFeaturesRepository(private val apiMapper: GetNbaFeaturesApiMapper) {

    suspend fun getFeatures(): List<NbaFeatureData> {
        return apiMapper.get().features
    }
}