package ru.svlit.sandbox.core.designsystem.item.adapter

import android.app.Activity

interface Event

interface NavigationEvent : Event {
    fun navigate(activity: Activity)
}

object Back : NavigationEvent {
    override fun navigate(activity: Activity) {
        activity.finish()
    }
}

object FinishEvent : NavigationEvent {
    override fun navigate(activity: Activity) {
        activity.finish()
    }
}