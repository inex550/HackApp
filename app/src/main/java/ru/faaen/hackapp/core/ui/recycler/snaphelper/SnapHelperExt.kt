package ru.faaen.hackapp.core.ui.recycler.snaphelper

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

fun SnapHelper.getSnapPosition(recycler: RecyclerView): Int {
    val layoutManager = recycler.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return recycler.getChildAdapterPosition(snapView)
}