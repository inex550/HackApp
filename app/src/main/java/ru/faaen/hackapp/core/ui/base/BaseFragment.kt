package ru.faaen.hackapp.core.ui.base

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import ru.faaen.hackapp.core.navigation.RouterProvider
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int
): Fragment(layoutResId) {

    open fun setupUi() = Unit

    open fun onKeyDown(keyCode: Int, event: KeyEvent?) = Unit

    open fun onBackPressed(): Boolean = false

    val viewLifecycle: Lifecycle
        get() = viewLifecycleOwner.lifecycle

    val localRouter: Router?
        get() = (parentFragment as? RouterProvider)?.provideRouter() ?: (activity as? RouterProvider)?.provideRouter()

    fun requireLocalRouter(): Router = requireNotNull(localRouter)

    fun showSnackbarError(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.RED)
            .setTextColor(Color.WHITE)
            .show()
    }

    fun showSnackbarSuccess(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.GREEN)
            .setTextColor(Color.WHITE)
            .show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUi()
    }
}