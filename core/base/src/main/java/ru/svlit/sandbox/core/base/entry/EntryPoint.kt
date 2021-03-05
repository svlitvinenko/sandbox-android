package ru.svlit.sandbox.core.base.entry

import android.app.Activity

/**
 * Точка входа.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
interface EntryPoint<A : FeatureArguments> {

    val isEnabled: Boolean

    fun launch(activity: Activity)
}