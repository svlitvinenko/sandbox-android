package ru.svlit.sandbox.feature.weather.presentation.event

import android.app.Activity
import android.content.Intent
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent
import ru.svlit.sandbox.feature.weather.presentation.ForecastActivity

/**
 * TНавигационное действие "Открытие прогноза погоды".
 *
 * @author Sergei Litvinenko on 07 Mar, 2021.
 */
object OpenForecastEvent : NavigationEvent {

    override fun navigate(activity: Activity) {
        val intent: Intent = ForecastActivity.newIntent(activity)
        activity.startActivity(intent)
    }
}