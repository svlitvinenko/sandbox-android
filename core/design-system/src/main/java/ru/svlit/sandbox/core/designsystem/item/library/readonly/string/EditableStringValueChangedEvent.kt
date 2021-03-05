package ru.svlit.sandbox.core.designsystem.item.library.readonly.string

import ru.svlit.sandbox.core.designsystem.item.adapter.Event

/**
 * Событие изменения значения.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class EditableStringValueChangedEvent(
    val id: String,
    val value: String
) : Event
