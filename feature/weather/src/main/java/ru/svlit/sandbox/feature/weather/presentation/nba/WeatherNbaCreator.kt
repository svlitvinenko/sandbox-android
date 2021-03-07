package ru.svlit.sandbox.feature.weather.presentation.nba

import ru.svlit.sandbox.core.designsystem.item.library.card.rectangle.RectangleCard
import ru.svlit.sandbox.core.designsystem.item.library.carousel.CarouselItem
import ru.svlit.sandbox.core.models.TextWrapper
import ru.svlit.sandbox.feature.nba.data.NbaCreator
import ru.svlit.sandbox.feature.nba.models.data.NbaFeatureData
import ru.svlit.sandbox.feature.weather.models.data.WeatherNbaData
import ru.svlit.sandbox.feature.weather.presentation.event.OpenForecastEvent

/**
 * Создатель NBA для продукта "Прогноз погоды".
 *
 * @author Sergei Litvinenko on 06 Mar, 2021.
 */
class WeatherNbaCreator : NbaCreator<WeatherNbaData> {
    override val featureKey: String = "weather"
    override val nbaDataClass: Class<out WeatherNbaData> = WeatherNbaData::class.java

    override fun create(nbaData: NbaFeatureData): CarouselItem {
        nbaData as WeatherNbaData
        return RectangleCard(
            id = "weather",
            title = TextWrapper("Прогноз погоды"),
            subtitle = TextWrapper("Сейчас ${nbaData.value} в ${nbaData.location}"),
            onClickEvent = OpenForecastEvent
        )
    }
}