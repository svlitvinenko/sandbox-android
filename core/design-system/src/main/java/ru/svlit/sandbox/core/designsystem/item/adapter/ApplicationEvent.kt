package ru.svlit.sandbox.core.designsystem.item.adapter

sealed class ApplicationEvent : Event {
    data class NavigationElementSelected(val id: Int) : ApplicationEvent()
    data class NavigationElementReselected(val id: Int) : ApplicationEvent()
}
