package ru.faaen.hackapp.features.wherego.presentation.vm

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.faaen.hackapp.core.ui.base.BaseViewModel

class WhereGoViewModel @AssistedInject constructor(
    @Assisted private val router: Router,
    private val uiBuilder: WhereGoUiBuilder,
): BaseViewModel<WhereGoViewState, WhereGoViewAction>(
    initialState = WhereGoViewState(
        filters = uiBuilder.getFilters(),
        items = uiBuilder.getItems(),
    )
) {

    override fun provideDefaultMessageError(message: String): WhereGoViewAction {
        return WhereGoViewAction.ShowSnackbarError(message)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted router: Router
        ): WhereGoViewModel
    }
}