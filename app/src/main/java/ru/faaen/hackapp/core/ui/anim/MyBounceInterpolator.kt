package ru.faaen.hackapp.core.ui.anim

import android.view.animation.Interpolator
import kotlin.math.cos
import kotlin.math.pow

class MyBounceInterpolator(
    private val amplitude: Double = 1.0,
    private val frequency: Double = 10.0,
): Interpolator {

    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.E.pow(-time / amplitude) * cos(frequency * time) + 1)
            .toFloat()
    }
}