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
 * Декорация [ItemDecoration], устанавливающий отступ [offsetRes] между элементами списка с разными `viewType`.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class DifferentViewTypesOffsetDecoration(
    @DimenRes private val offsetRes: Int = R.dimen.margin_small,
    private val orientation: Orientation = VERTICAL
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        if (position <= 0) return
        val currentItemViewType: Int = parent.adapter?.getItemViewType(position) ?: return
        val previousItemViewType: Int = parent.adapter?.getItemViewType(position - 1) ?: return
        if (currentItemViewType != 0 && previousItemViewType != 0 && currentItemViewType != previousItemViewType) {
            val offsetPixels = view.context.resources.getDimensionPixelOffset(offsetRes)
            when (orientation) {
                HORIZONTAL -> outRect.left = offsetPixels
                VERTICAL -> outRect.top = offsetPixels
            }
        }
    }
}