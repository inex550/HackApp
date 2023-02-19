package ru.faaen.hackapp.features.news.vm

import ru.faaen.hackapp.core.ui.base.BaseViewAction

sealed interface NewsViewAction: BaseViewAction {
    data class ShowSnackbarError(val message: String): NewsViewAction
}