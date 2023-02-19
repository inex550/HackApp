package ru.faaen.hackapp.features.news.vm

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.faaen.hackapp.core.ui.base.BaseViewModel
import ru.faaen.hackapp.features.events.presentation.vm.EventsUiBuilder
import ru.faaen.hackapp.features.news.data.repo.NewsRepository
import ru.faaen.hackapp.features.news.presentation.adapter.NewsItem

class NewsViewModel @AssistedInject constructor(
    @Assisted private val router: Router,
    private val repository: NewsRepository,
    private val uiBuilder: EventsUiBuilder<NewsItem>
): BaseViewModel<NewsViewState, NewsViewAction>(
    initialState = NewsViewState(
        filters = uiBuilder.getFilters(),
        items = uiBuilder.getItems()
    )
) {
    init {
        loadEvents()
    }

    override fun provideDefaultMessageError(message: String): NewsViewAction {
        return NewsViewAction.ShowSnackbarError(message)
    }

    private fun loadEvents() {
        launchIOCoroutine(
            finallyListener = { updateState { copy(items = uiBuilder.getItems()) } }
        ) {
            val events = repository.loadNews()

            uiBuilder.clearShimmer()
            uiBuilder.setItems(events)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted router: Router
        ): NewsViewModel
    }
}