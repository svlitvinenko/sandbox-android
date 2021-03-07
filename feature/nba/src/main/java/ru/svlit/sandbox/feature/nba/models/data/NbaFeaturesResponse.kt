package ru.svlit.sandbox.feature.nba.models.data

import com.google.gson.annotations.SerializedName

/**
 * Модель ответа NBA.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
data class NbaFeaturesResponse(
    @SerializedName("features")
    val features: List<NbaFeatureData>
)