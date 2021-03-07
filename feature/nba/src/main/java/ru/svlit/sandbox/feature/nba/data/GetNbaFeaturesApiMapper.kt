package ru.svlit.sandbox.feature.nba.data

import ru.svlit.sandbox.feature.nba.models.data.NbaFeaturesResponse

/**
 * Api-маппер NBA.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
interface GetNbaFeaturesApiMapper {

    suspend fun get(): NbaFeaturesResponse
}