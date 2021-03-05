package ru.svlit.sandbox.core.designsystem.item.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.item.decoration.Orientation.HORIZONTAL
import ru.svlit.sandbox.core.designsystem.item.decoration.Orientation.VERTICAL

/**
 * Декорация [ItemDecoration], устанавливающая конечный отступ для последнего элемента.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class LastItemOffsetDecoration(
    @DimenRes private val offsetRes: Int = R.dimen.margin_small,
    private val orientation: Orientation = VERTICAL
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition: Int = parent.getChildAdapterPosition(view)
        val lastPosition: Int = (parent.adapter?.itemCount ?: return) - 1

        if (itemPosition == lastPosition) {
            val offsetPixels: Int = view.context.resources.getDimensionPixelOffset(offsetRes)
            when (orientation) {
                HORIZONTAL -> outRect.right = offsetPixels
                VERTICAL -> outRect.bottom = offsetPixels
            }
        }
    }
}