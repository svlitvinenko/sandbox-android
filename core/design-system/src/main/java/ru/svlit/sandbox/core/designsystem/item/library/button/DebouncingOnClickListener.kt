package ru.svlit.sandbox.core.designsystem.item.library.button

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Debouncing-слушатель нажатий.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
fun <T> debounce(delayMs: Long = 500, coroutineScope: CoroutineScope, action: (T) -> Unit): (T) -> Unit {
    var job: Job? = null
    return { value: T ->
        if (job == null) {
            job = coroutineScope.launch {
                action.invoke(value)
                delay(delayMs)
                job = null
            }
        }
    }
}