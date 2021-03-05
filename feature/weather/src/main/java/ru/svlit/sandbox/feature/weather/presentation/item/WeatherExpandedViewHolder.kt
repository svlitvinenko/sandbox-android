package ru.svlit.sandbox.feature.weather.presentation.item

import android.view.View
import android.widget.TextView
import ru.svlit.sandbox.core.designsystem.item.adapter.ItemViewHolder
import ru.svlit.sandbox.feature.weather.R
import ru.svlit.sandbox.feature.weather.models.domain.WeatherMetrics.CELSIUS
import ru.svlit.sandbox.feature.weather.models.domain.WeatherMetrics.FAHRENHEIT
import ru.svlit.sandbox.feature.weather.models.presentation.WeatherExpanded

/**
 * [ItemViewHolder] для отрисовки [WeatherExpanded].
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class WeatherExpandedViewHolder(itemView: View) : ItemViewHolder<WeatherExpanded>(itemView) {

    private val temperatureValueTextView: TextView = itemView.findViewById(R.id.temperature_value_text_view)
    private val temperatureMetricsTextView: TextView = itemView.findViewById(R.id.temperature_metrics_text_view)
    private val temperatureRegionTextView: TextView = itemView.findViewById(R.id.temperature_region_text_view)

    override fun bind(item: WeatherExpanded) {
        temperatureRegionTextView.text = item.region
        temperatureValueTextView.text = item.temperature.value.toPlainString()
        temperatureMetricsTextView.text = when (item.temperature.metrics) {
            CELSIUS -> itemView.context.getString(R.string.celsius_degree)
            FAHRENHEIT -> itemView.context.getString(R.string.fahrenheit_degree)
        }
    }
}
