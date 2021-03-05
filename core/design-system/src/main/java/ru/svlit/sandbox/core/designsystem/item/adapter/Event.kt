package ru.svlit.sandbox.core.designsystem.item.adapter

import android.app.Activity

interface Event

interface NavigationEvent : Event {
    fun navigate(activity: Activity)
}

object Back : Event

object FinishEvent : NavigationEvent {
    override fun navigate(activity: Activity) {
        activity.finish()
    }
}