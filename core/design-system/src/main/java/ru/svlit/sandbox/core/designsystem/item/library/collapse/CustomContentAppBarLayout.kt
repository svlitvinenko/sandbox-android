package ru.svlit.sandbox.core.designsystem.item.library.collapse

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import com.google.android.material.appbar.AppBarLayout
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.setEnabledRecursively
import kotlin.math.pow

/**
 * [AppBarLayout], показывающий разный контент в скрытом и раскрытом состояниях.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class CustomContentAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppBarLayout(context, attrs, defStyleAttr) {

    private val collapsedContentContainer: FrameLayout
    private val expandedContentContainer: FrameLayout

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomContentAppBarLayout)
        @LayoutRes val collapsedContentRes: Int = attributes.getResourceId(R.styleable.CustomContentAppBarLayout_ccablCollapsedContent, 0)
        @LayoutRes val expandedContentRes: Int = attributes.getResourceId(R.styleable.CustomContentAppBarLayout_ccablExpandedContent, 0)
        @DrawableRes val backgroundRes: Int = attributes.getResourceId(R.styleable.CustomContentAppBarLayout_ccablBackground, 0)
        attributes.recycle()

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.ds_internal_custom_content_app_bar_layout, this, true)
        collapsedContentContainer = view.findViewById(R.id.collapsed_content_container)
        expandedContentContainer = view.findViewById(R.id.expanded_content_container)

        inflateCollapsedContent(inflater, collapsedContentRes)
        inflateExpandedContent(inflater, expandedContentRes)
        setExpandedContentBackground(backgroundRes)

        addOnOffsetChangedListener(ScrollChangeListener(this))
    }

    fun setExpandedContentBackground(@DrawableRes backgroundRes: Int) {
        findViewById<View>(R.id.background_view).setBackgroundResource(backgroundRes)
    }

    fun getToolbar(): Toolbar {
        return findViewById(R.id.toolbar)
    }

    fun getCollapsedView(): View {
        return collapsedContentContainer.children.first()
    }

    fun getExpandedView(): View {
        return expandedContentContainer.children.first()
    }

    private fun inflateCollapsedContent(inflater: LayoutInflater, @LayoutRes collapsedContentRes: Int) {
        require(collapsedContentRes != 0) { "Collapsed content must be set" }
        inflater.inflate(collapsedContentRes, collapsedContentContainer, true)
    }

    private fun inflateExpandedContent(inflater: LayoutInflater, @LayoutRes expandedContentRes: Int) {
        require(expandedContentRes != 0) { "Expanded content must be set" }
        inflater.inflate(expandedContentRes, expandedContentContainer, true)
    }

    private class ScrollChangeListener(
        private val appBarLayout: CustomContentAppBarLayout
    ) : RelativeOnOffsetChangedListener() {

        override fun onRelativeOffsetChanged(relativeOffset: Float) {
            updateExpandedContent(relativeOffset)
            updateCollapsedContent(relativeOffset)
        }

        private fun updateExpandedContent(relativeOffset: Float) {
            appBarLayout.collapsedContentContainer.alpha = expandedContentFunction(relativeOffset)
            appBarLayout.expandedContentContainer.setEnabledRecursively(relativeOffset < 0.99)
            appBarLayout.expandedContentContainer.visibility = if (relativeOffset < 0.99) VISIBLE else INVISIBLE
        }

        private fun expandedContentFunction(relativeOffset: Float): Float {
            return relativeOffset.toDouble().pow(4.0).toFloat()
        }

        private fun updateCollapsedContent(relativeOffset: Float) {
            appBarLayout.expandedContentContainer.alpha = collapsedContentFunction(relativeOffset)
            appBarLayout.collapsedContentContainer.setEnabledRecursively(relativeOffset > 0.01)
            appBarLayout.collapsedContentContainer.visibility = if (relativeOffset > 0.01) VISIBLE else INVISIBLE
        }

        private fun collapsedContentFunction(relativeOffset: Float): Float {
            return (1 - relativeOffset).toDouble().pow(4.0).toFloat()
        }
    }
}