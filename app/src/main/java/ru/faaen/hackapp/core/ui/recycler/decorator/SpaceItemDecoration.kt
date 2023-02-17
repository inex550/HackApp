package ru.faaen.hackapp.core.ui.recycler.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration: RecyclerView.ItemDecoration() {

    private var viewType: Int = -1

    fun setViewType(viewType: Int) {
        this.viewType = viewType
    }

    var left: Int = 0
    var top: Int = 0
    var right: Int = 0
    var bottom: Int = 0

    fun setHorizontal(space: Int) {
        left = space
        right = space
    }

    fun setVertical(space: Int) {
        top = space
        bottom = space
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position < 0) return

        val itemViewType = if (viewType == -1) {
            -1
        } else {
            parent.adapter?.getItemViewType(position) ?: -1
        }

        if (itemViewType == viewType) {
            outRect.left = left
            outRect.top = top
            outRect.right = right
            outRect.bottom = bottom
        }
    }
}