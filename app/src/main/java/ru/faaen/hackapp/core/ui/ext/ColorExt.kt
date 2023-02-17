package ru.faaen.hackapp.core.ui.ext

import android.graphics.Color
import android.view.View
import android.view.animation.ScaleAnimation
import androidx.annotation.ColorInt
import androidx.core.view.isGone
import androidx.core.view.isVisible

private const val DEFAULT_DURATION = 200L

@ColorInt
fun Int.adjustAlpha(alpha: Int): Int {
    return Color.argb(
        alpha,
        Color.red(this),
        Color.green(this),
        Color.blue(this),
    )
}

@ColorInt
fun Int.adjustAlpha(alpha: Float): Int {
    return Color.argb(
        (alpha * 255f + 0.5f).toInt(),
        Color.red(this),
        Color.green(this),
        Color.blue(this)
    )
}