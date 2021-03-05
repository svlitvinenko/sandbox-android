package ru.svlit.sandbox.feature.host.presentation.event

import android.app.Activity
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent
import ru.svlit.sandbox.feature.fines.presentation.FinesActivity.Companion.newIntent
import ru.svlit.sandbox.feature.fines.presentation.FinesArguments

/**
 * Запуск Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class LaunchAddServiceEvent(val arguments: FinesArguments) : NavigationEvent {
    override fun navigate(activity: Activity) {
        activity.startActivity(newIntent(activity, arguments))
    }
}