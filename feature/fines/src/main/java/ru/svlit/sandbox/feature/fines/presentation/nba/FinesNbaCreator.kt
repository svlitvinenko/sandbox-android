package ru.svlit.sandbox.feature.fines.presentation.nba

import ru.svlit.sandbox.core.designsystem.item.library.card.square.SquareCard
import ru.svlit.sandbox.core.designsystem.item.library.carousel.CarouselItem
import ru.svlit.sandbox.core.models.TextWrapper
import ru.svlit.sandbox.core.models.TextWrapper.ByPlural
import ru.svlit.sandbox.feature.fines.R
import ru.svlit.sandbox.feature.fines.presentation.FinesArguments
import ru.svlit.sandbox.feature.fines.presentation.entry.FinesNbaFeatureData
import ru.svlit.sandbox.feature.fines.presentation.event.OpenFinesEvent
import ru.svlit.sandbox.feature.nba.data.NbaCreator
import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData

/**
 * Создатель NBA Штрафов.
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
class FinesNbaCreator : NbaCreator<FinesNbaFeatureData> {

    override val featureKey = "fines"

    override val nbaDataClass: Class<FinesNbaFeatureData> = FinesNbaFeatureData::class.java

    override fun create(nbaData: NbaFeatureData): CarouselItem {
        nbaData as FinesNbaFeatureData
        return SquareCard(
            id = "fines",
            text = ByPlural(R.plurals.number_of_fines, nbaData.found, listOf(nbaData.found.toString())),
            onClickEvent = OpenFinesEvent(FinesArguments(nbaData.found))
        )
    }
}