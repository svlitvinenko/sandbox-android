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
 * Декорация [ItemDecoration], устанвливающая отступ [offsetRes] между каждым элементом списка.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class BetweenItemOffsetDecoration(
    @DimenRes private val offsetRes: Int = R.dimen.margin_small,
    private val orientation: Orientation = VERTICAL
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition: Int = parent.getChildAdapterPosition(view)

        if (itemPosition != 0) {
            if (orientation == HORIZONTAL) {
                outRect.left = view.context.resources.getDimensionPixelOffset(offsetRes)
            } else {
                outRect.bottom = view.context.resources.getDimensionPixelOffset(offsetRes)
            }
        }
    }

}
