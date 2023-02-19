package ru.faaen.hackapp.features.shop.vm

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import ru.faaen.hackapp.core.ui.base.BaseViewModel

class ShopViewModel @AssistedInject constructor (
    private val uiBuilder: ShopUiBuilder
): BaseViewModel<ShopViewState, ShopViewAction>(
    initialState = ShopViewState(
        items = uiBuilder.getItems()
    )
) {
    init {
        loadProducts()
    }

    override fun provideDefaultMessageError(message: String): ShopViewAction {
        return ShopViewAction.ShowSnackbarError(message)
    }

    private fun loadProducts() {
        launchIOCoroutine(
            finallyListener = { updateState { copy(items = uiBuilder.getItems()) } }
        ) {
            delay(1000)

            uiBuilder.clearShimmer()
            uiBuilder.setProducts()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): ShopViewModel
    }
}