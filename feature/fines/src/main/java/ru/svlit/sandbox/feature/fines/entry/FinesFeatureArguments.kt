package ru.svlit.sandbox.feature.fines.entry

import ru.svlit.sandbox.core.base.entry.FeatureArguments

/**
 * Аргументы продукта Штрафы.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
data class FinesFeatureArguments(
    val name: ru.svlit.sandbox.core.models.TextWrapper
) : FeatureArguments