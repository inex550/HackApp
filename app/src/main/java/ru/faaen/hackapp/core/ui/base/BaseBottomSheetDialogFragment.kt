package ru.faaen.hackapp.core.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment(
    @LayoutRes private val layoutResId: Int
): BottomSheetDialogFragment() {

    open fun setupUi() = Unit

    open fun setupInjection() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInjection()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUi()
    }

    fun delayedDismiss(delay: Long = DEFAULT_DISMISS_DELAY) {
        Handler(Looper.getMainLooper()).postDelayed({ dismiss() }, delay)
    }

    companion object {
        const val DEFAULT_DISMISS_DELAY = 500L
    }
}