package ru.faaen.hackapp.features.auth.presentation.vm

import ru.faaen.hackapp.core.common.ext.SpecError
import ru.faaen.hackapp.core.ui.base.BaseViewAction

sealed interface AuthViewAction: BaseViewAction {
    data class ShowSnackbarError(val message: String): AuthViewAction
    data class ShowSpec(val spec: SpecError): AuthViewAction
}