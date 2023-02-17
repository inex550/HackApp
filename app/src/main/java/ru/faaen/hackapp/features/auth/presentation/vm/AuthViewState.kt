package ru.faaen.hackapp.features.auth.presentation.vm

import ru.faaen.hackapp.core.ui.base.BaseViewState

data class AuthViewState(
    val isLoading: Boolean = false,
): BaseViewState