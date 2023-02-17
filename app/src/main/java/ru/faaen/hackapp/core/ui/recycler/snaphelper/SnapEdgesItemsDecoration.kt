package ru.faaen.hackapp.core.ui.recycler.snaphelper

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.faaen.hackapp.core.ui.ext.getScreenWidth

class SnapEdgesItemsDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val space = getScreenWidth() / 2 + view.width / 2

        val position = parent.getChildAdapterPosition(view)
        val isLast = position == state.itemCount - 1

        if (position == 0) {
            outRect.left = space
        }

        if (isLast) {
            outRect.right = space
        }
    }
}