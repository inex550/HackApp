package ru.faaen.hackapp.core.ui.ext

import android.view.View

fun View.measureDimension(desiredSize: Int, measureSpec: Int): Int {
    var result = 0
    val mode = View.MeasureSpec.getMode(measureSpec)
    val size = View.MeasureSpec.getSize(measureSpec)

    if (mode == View.MeasureSpec.EXACTLY) {
        result = size
    } else {
        result = desiredSize
        if (mode == View.MeasureSpec.AT_MOST) {
            result = desiredSize.coerceAtMost(size)
        }
    }

    return result
}