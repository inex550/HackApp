package ru.faaen.hackapp.features.shop.vm

import ru.faaen.hackapp.core.ui.base.BaseViewAction

sealed interface ShopViewAction: BaseViewAction {
    data class ShowSnackbarError(val message: String): ShopViewAction
}