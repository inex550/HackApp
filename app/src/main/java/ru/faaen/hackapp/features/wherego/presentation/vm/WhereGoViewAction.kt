package ru.faaen.hackapp.features.wherego.presentation.vm

import ru.faaen.hackapp.core.ui.base.BaseViewAction

sealed interface WhereGoViewAction: BaseViewAction {

    data class ShowSnackbarError(val message: String): WhereGoViewAction
}