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
 * Декорация [ItemDecoration], устанавливающая начальный отступ для первого элемента.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class FirstItemOffsetDecoration(
    @DimenRes private val offsetRes: Int = R.dimen.margin_small,
    private val orientation: Orientation = VERTICAL
) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition: Int = parent.getChildAdapterPosition(view)

        if (itemPosition == 0) {
            val offsetPixels: Int = view.context.resources.getDimensionPixelOffset(offsetRes)
            when (orientation) {
                HORIZONTAL -> outRect.left = offsetPixels
                VERTICAL -> outRect.top = offsetPixels
            }
        }
    }
}