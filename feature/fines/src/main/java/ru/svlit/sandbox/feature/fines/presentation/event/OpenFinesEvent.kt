package ru.svlit.sandbox.feature.fines.presentation.event

import android.app.Activity
import android.content.Intent
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent
import ru.svlit.sandbox.feature.fines.presentation.FinesActivity
import ru.svlit.sandbox.feature.fines.presentation.FinesArguments

/**
 * Навигационное событие "Открытие Штрафов".
 *
 * @author Sergei Litvinenko on 07 Mar, 2021.
 */
data class OpenFinesEvent(private val arguments: FinesArguments) : NavigationEvent {

    override fun navigate(activity: Activity) {
        val intent: Intent = FinesActivity.newIntent(activity, arguments)
        activity.startActivity(intent)
    }
}