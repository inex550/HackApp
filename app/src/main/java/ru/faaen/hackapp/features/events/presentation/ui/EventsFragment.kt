package ru.faaen.hackapp.features.events.presentation.ui

import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.adapter.ShimmerAdapter
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.ui.base.BaseMvvmFragment
import ru.faaen.hackapp.core.ui.ext.renderIn
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.ext.viewModels
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter
import ru.faaen.hackapp.databinding.FragmentEventsBinding
import ru.faaen.hackapp.features.events.presentation.ui.adapter.EventsAdapter
import ru.faaen.hackapp.features.events.presentation.ui.adapter.EventsItem
import ru.faaen.hackapp.features.events.presentation.vm.EventsViewAction
import ru.faaen.hackapp.features.events.presentation.vm.EventsViewModel
import ru.faaen.hackapp.features.events.presentation.vm.EventsViewState
import ru.faaen.hackapp.features.wherego.data.models.FilterOption
import ru.faaen.hackapp.features.wherego.data.models.Filters
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterAdapter
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterDateAdapter
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterDateItem
import javax.inject.Inject

@AndroidEntryPoint
class EventsFragment: BaseMvvmFragment<EventsViewState, EventsViewAction, EventsViewModel>(
    layoutResId = R.layout.fragment_events
) {
    private val binding: FragmentEventsBinding by viewBinding(FragmentEventsBinding::bind)

    @Inject
    lateinit var viewModelFactory: EventsViewModel.Factory

    override val viewModel: EventsViewModel by viewModels {
        viewModelFactory.create(requireLocalRouter())
    }

    private val filtersAdapter: ConcatAdapter get() = binding.filtersRv.adapter as ConcatAdapter
    private val itemsAdapter: ConcatAdapter get() = binding.itemsRv.adapter as ConcatAdapter

    private val filterAdapterListener: FilterAdapter.Listener by uiLazy {
        object : FilterAdapter.Listener {
            override fun onNewFilterOptionSelected(filter: Filters, option: FilterOption?) {

            }
        }
    }

    private val filterDateAdapterListener: FilterDateAdapter.Listener by uiLazy {
        object : FilterDateAdapter.Listener {
            override fun onNewDateSelected(item: FilterDateItem) {

            }
        }
    }

    private val eventsAdapterListener: EventsAdapter.Listener by uiLazy {
        object : EventsAdapter.Listener {
            override fun onEventClicked(item: EventsItem) {

            }
        }
    }

    override fun setupUi() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            filtersRv.adapter = ConcatAdapter(
                FilterAdapter(filterAdapterListener),
                FilterDateAdapter(filterDateAdapterListener),
            )

            itemsRv.adapter = ConcatAdapter(
                EventsAdapter(eventsAdapterListener),
                ShimmerAdapter(),
            )
        }
    }

    override fun processAction(action: EventsViewAction) {
        when (action) {
            is EventsViewAction.ShowSnackbarError -> {
                showSnackbarError(action.message)
            }
        }
    }

    override fun setupRender() {
        with(viewModel.uiState) {
            renderIn(viewLifecycle, { it.filters }, ::renderFilters)
            renderIn(viewLifecycle, { it.items }, ::renderItems)
        }
    }

    private fun renderFilters(filters: List<BaseItem>) {
        filtersAdapter.setItems(filters)
    }

    private fun renderItems(items: List<BaseItem>) {
        itemsAdapter.setItems(items)
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}