package ru.svlit.sandbox.feature.nba.data

import ru.svlit.sandbox.core.designsystem.item.library.carousel.CarouselItem
import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

/**
 * Создатель элемента NBA.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
interface NbaCreator<T : NbaFeatureData> {
    val featureKey: String
    val nbaDataClass: Class<out T>
    fun create(nbaData: NbaFeatureData): CarouselItem
}