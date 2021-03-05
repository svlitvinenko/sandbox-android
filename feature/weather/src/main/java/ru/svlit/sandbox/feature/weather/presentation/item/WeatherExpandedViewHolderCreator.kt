package ru.svlit.sandbox.feature.weather.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolderCreator
import ru.svlit.sandbox.feature.weather.R
import ru.svlit.sandbox.feature.weather.models.presentation.WeatherExpanded

/**
 * [ItemViewHolderCreator], создающий [WeatherExpandedViewHolder].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class WeatherExpandedViewHolderCreator : ItemViewHolderCreator<WeatherExpanded> {

    override fun create(parent: ViewGroup, attachToRoot: Boolean, eventListener: EventListener): ItemViewHolder<WeatherExpanded> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_expanded, parent, attachToRoot)
        return WeatherExpandedViewHolder(view)
    }
}
