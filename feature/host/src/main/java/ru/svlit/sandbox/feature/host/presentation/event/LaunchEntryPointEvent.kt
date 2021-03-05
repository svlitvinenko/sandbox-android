package ru.svlit.sandbox.feature.host.presentation.event

import android.app.Activity
import ru.svlit.sandbox.core.base.entry.EntryPoint
import ru.svlit.sandbox.core.base.entry.FeatureArguments
import ru.svlit.sandbox.core.designsystem.item.adapter.NavigationEvent

/**
 * Активация точки входа.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class LaunchEntryPointEvent<A : FeatureArguments, E : EntryPoint<A>>(
    private val entryPoint: E
) : NavigationEvent {

    override fun navigate(activity: Activity) {
        entryPoint.launch(activity)
    }
}
