package ru.faaen.hackapp.features.news.presentation

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
import ru.faaen.hackapp.databinding.FragmentNewsBinding
import ru.faaen.hackapp.features.news.presentation.adapter.NewsAdapter
import ru.faaen.hackapp.features.news.presentation.adapter.NewsItem
import ru.faaen.hackapp.features.news.vm.NewsViewAction
import ru.faaen.hackapp.features.news.vm.NewsViewModel
import ru.faaen.hackapp.features.news.vm.NewsViewState
import ru.faaen.hackapp.features.wherego.data.models.FilterOption
import ru.faaen.hackapp.features.wherego.data.models.Filters
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterAdapter
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterDateAdapter
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterDateItem
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment: BaseMvvmFragment<NewsViewState, NewsViewAction, NewsViewModel>(
    layoutResId = R.layout.fragment_news
) {
    private val binding: FragmentNewsBinding by viewBinding(FragmentNewsBinding::bind)

    @Inject
    lateinit var viewModelFactory: NewsViewModel.Factory

    override val viewModel: NewsViewModel by viewModels {
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

    private val eventsAdapterListener: NewsAdapter.Listener by uiLazy {
        object : NewsAdapter.Listener {
            override fun onEventClicked(item: NewsItem) {

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
                NewsAdapter(eventsAdapterListener),
                ShimmerAdapter(),
            )
        }
    }

    override fun processAction(action: NewsViewAction) {
        when (action) {
            is NewsViewAction.ShowSnackbarError -> {
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