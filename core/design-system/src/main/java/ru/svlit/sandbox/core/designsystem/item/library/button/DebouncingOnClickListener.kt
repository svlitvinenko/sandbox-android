package ru.svlit.sandbox.core.designsystem.item.library.button

import android.os.Handler
import android.os.Looper
import android.view.View

/**
 * Debouncing-слушатель нажатий.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
data class DebouncingOnClickListener(
    private val timeoutMs: Long = 200L,
    private val onClickListener: View.OnClickListener
) : View.OnClickListener {

    override fun onClick(view: View) {
        handler.postDelayed({ onClickListener.onClick(view) }, timeoutMs)
    }

    companion object {
        private val handler: Handler = Handler(Looper.getMainLooper())

        fun debounce(
            timeoutMs: Long = 200L,
            onClickListener: View.OnClickListener
        ) = DebouncingOnClickListener(timeoutMs, onClickListener)
    }
}