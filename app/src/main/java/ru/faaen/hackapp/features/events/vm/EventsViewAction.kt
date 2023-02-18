package ru.faaen.hackapp.features.events.vm

import ru.faaen.hackapp.core.ui.base.BaseViewAction

sealed interface EventsViewAction: BaseViewAction {
    data class ShowSnackbarError(val message: String): EventsViewAction
}