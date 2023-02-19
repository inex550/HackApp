package ru.faaen.hackapp.features.wherego.presentation.vm

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.faaen.hackapp.core.ui.base.BaseViewModel
import ru.faaen.hackapp.features.events.presentation.vm.EventsUiBuilder
import ru.faaen.hackapp.features.wherego.data.repo.WhereGoRepository
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.WherePlaceItem

class WhereGoViewModel @AssistedInject constructor(
    @Assisted private val router: Router,
    private val repository: WhereGoRepository,
    private val uiBuilder: EventsUiBuilder<WherePlaceItem>,
): BaseViewModel<WhereGoViewState, WhereGoViewAction>(
    initialState = WhereGoViewState(
        filters = uiBuilder.getFilters(),
        items = uiBuilder.getItems(),
    )
) {

    override fun provideDefaultMessageError(message: String): WhereGoViewAction {
        return WhereGoViewAction.ShowSnackbarError(message)
    }

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        launchIOCoroutine(
            finallyListener = { updateState { copy(items = uiBuilder.getItems()) } }
        ) {
            val locations = repository.loadPlaces()

            uiBuilder.clearShimmer()
            uiBuilder.setLocations(locations)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted router: Router
        ): WhereGoViewModel
    }
}