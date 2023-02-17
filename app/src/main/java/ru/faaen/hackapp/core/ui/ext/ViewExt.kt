package ru.faaen.hackapp.core.ui.ext

import android.content.res.Resources.getSystem
import android.util.DisplayMetrics
import android.view.View

private val displayMetrics: DisplayMetrics
    get() = getSystem().displayMetrics

val Int.dp: Int get() = (this * displayMetrics.density).toInt()

val Float.dp: Float get() = this * displayMetrics.density

val Int.toDp: Int get() = (this / displayMetrics.density).toInt()

val Float.toDp: Float get() = this / displayMetrics.density

fun getScreenWidth(): Int {
    return displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return displayMetrics.heightPixels
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}