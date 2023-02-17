package ru.faaen.hackapp.core.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class BaseMvvmFragment <VS: BaseViewState, VA: BaseViewAction, VM: BaseViewModel<VS, VA>>(
    @LayoutRes layoutResId: Int
): BaseFragment(layoutResId) {

    open fun setupRender() = Unit
    open fun processAction(action: VA) = Unit

    abstract val viewModel: VM

    protected val uiState: VS
        get() = viewModel.uiState.value

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRender()

        lifecycleScope.launch {
            viewModel.action.flowWithLifecycle(
                lifecycle = viewLifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect(::processAction)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}