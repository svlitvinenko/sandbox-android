package ru.svlit.sandbox.feature.fines.presentation.entry

import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

/**
 * Модель ответа NBA для штрафов.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
data class FinesNbaFeatureData(
    val found: Int,
    val sum: Int
) : NbaFeatureData
