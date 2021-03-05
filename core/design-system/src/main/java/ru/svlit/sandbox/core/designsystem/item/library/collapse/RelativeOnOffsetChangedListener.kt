package ru.svlit.sandbox.core.designsystem.item.library.collapse

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.absoluteValue

abstract class RelativeOnOffsetChangedListener : AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        val absoluteTotalScrollRange: Float = appBarLayout.totalScrollRange.absoluteValue.toFloat()
        val absoluteVerticalOffset: Float = verticalOffset.absoluteValue.toFloat()

        if (absoluteTotalScrollRange != 0f) {
            val relativeOffset: Float = absoluteVerticalOffset / absoluteTotalScrollRange
            onRelativeOffsetChanged(relativeOffset)
        }
    }

    abstract fun onRelativeOffsetChanged(relativeOffset: Float)
}