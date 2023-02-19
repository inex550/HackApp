package ru.faaen.hackapp.features.events.presentation.vm

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.faaen.hackapp.core.ui.base.BaseViewModel
import ru.faaen.hackapp.features.events.data.repo.EventsRepository
import ru.faaen.hackapp.features.events.presentation.ui.adapter.EventsItem

class EventsViewModel @AssistedInject constructor(
    @Assisted private val router: Router,
    private val repository: EventsRepository,
    private val uiBuilder: EventsUiBuilder<EventsItem>,
): BaseViewModel<EventsViewState, EventsViewAction>(
    initialState = EventsViewState(
        filters = uiBuilder.getFilters(),
        items = uiBuilder.getItems()
    )
) {
    init {
        loadEvents()
    }

    override fun provideDefaultMessageError(message: String): EventsViewAction {
        return EventsViewAction.ShowSnackbarError(message)
    }

    private fun loadEvents() {
        launchIOCoroutine(
            finallyListener = { updateState { copy(items = uiBuilder.getItems()) } }
        ) {
            val events = repository.loadEvents()

            uiBuilder.clearShimmer()
            uiBuilder.setEvents(events)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted router: Router
        ): EventsViewModel
    }
}