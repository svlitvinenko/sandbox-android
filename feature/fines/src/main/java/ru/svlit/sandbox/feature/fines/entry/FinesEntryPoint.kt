package ru.svlit.sandbox.feature.fines.entry

import android.app.Activity
import ru.svlit.sandbox.core.base.entry.EntryPoint
import ru.svlit.sandbox.core.models.TextWrapper.ByString
import ru.svlit.sandbox.feature.fines.presentation.FinesActivity
import ru.svlit.sandbox.feature.fines.presentation.FinesArguments

/**
 * Точка входа в Штрафы.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class FinesEntryPoint : EntryPoint<FinesFeatureArguments> {

    override val isEnabled: Boolean = true

    override fun launch(activity: Activity) {
        activity.startActivity(FinesActivity.newIntent(activity, FinesArguments(ByString("Штрафы"))))
    }
}