package ru.faaen.hackapp.core.ui.recycler.snaphelper

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class SnapPositionListener(
    private val snapHelper: SnapHelper,
    private val behavior: Behavior,
    private val listener: Listener,
): RecyclerView.OnScrollListener() {

    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL) {
            checkSnapPosition(recyclerView)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE) {
            checkSnapPosition(recyclerView)
        }
    }

    private fun checkSnapPosition(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            listener.onSnapPositionChanged(snapPosition)
            this.snapPosition = snapPosition
        }
    }

    enum class Behavior {
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE,
    }

    interface Listener {
        fun onSnapPositionChanged(position: Int)
    }
}