package ru.faaen.hackapp.features.wherego.presentation.ui

import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.utils.uiLazy
import ru.faaen.hackapp.core.ui.base.BaseMvvmFragment
import ru.faaen.hackapp.core.ui.ext.renderIn
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.ext.viewModels
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter
import ru.faaen.hackapp.databinding.FragmentWheregoBinding
import ru.faaen.hackapp.features.wherego.data.models.FilterOption
import ru.faaen.hackapp.features.wherego.data.models.Filters
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.FilterAdapter
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.WherePlaceItem
import ru.faaen.hackapp.features.wherego.presentation.ui.adapter.WherePlacesAdapter
import ru.faaen.hackapp.features.wherego.presentation.vm.WhereGoViewAction
import ru.faaen.hackapp.features.wherego.presentation.vm.WhereGoViewModel
import ru.faaen.hackapp.features.wherego.presentation.vm.WhereGoViewState
import javax.inject.Inject

@AndroidEntryPoint
class WhereGoFragment: BaseMvvmFragment<WhereGoViewState, WhereGoViewAction, WhereGoViewModel>(
    layoutResId = R.layout.fragment_wherego
) {
    private val binding by viewBinding(FragmentWheregoBinding::bind)

    @Inject
    lateinit var viewModelFactory: WhereGoViewModel.Factory

    override val viewModel: WhereGoViewModel by viewModels {
        viewModelFactory.create(requireLocalRouter())
    }

    private val filtersAdapter: ConcatAdapter get() = binding.filtersRv.adapter as ConcatAdapter
    private val itemsAdapter: ConcatAdapter get() = binding.itemsRv.adapter as ConcatAdapter

    private val filterAdapterListener: FilterAdapter.Listener by uiLazy {
        object : FilterAdapter.Listener {
            override fun onNewFilterOptionSelected(filter: Filters, option: FilterOption?) {
                showSnackbarSuccess(option?.name ?: filter.title)
            }
        }
    }

    private val wherePlaceAdapterListener: WherePlacesAdapter.Listener by uiLazy {
        object : WherePlacesAdapter.Listener {
            override fun placeClicked(item: WherePlaceItem) {

            }
        }
    }

    override fun setupUi() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            filtersRv.adapter = ConcatAdapter(
                FilterAdapter(filterAdapterListener)
            )

            itemsRv.adapter = ConcatAdapter(
                WherePlacesAdapter(wherePlaceAdapterListener)
            )
        }
    }

    override fun processAction(action: WhereGoViewAction) {
        when (action) {
            is WhereGoViewAction.ShowSnackbarError -> {
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