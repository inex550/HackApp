package ru.faaen.hackapp.features.shop.ui

import dagger.hilt.android.AndroidEntryPoint
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.adapter.ShimmerAdapter
import ru.faaen.hackapp.core.common.adapter.ShimmerItem
import ru.faaen.hackapp.core.ui.base.BaseMvvmFragment
import ru.faaen.hackapp.core.ui.ext.renderIn
import ru.faaen.hackapp.core.ui.ext.viewBinding
import ru.faaen.hackapp.core.ui.ext.viewModels
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter
import ru.faaen.hackapp.databinding.FragmentShopBinding
import ru.faaen.hackapp.features.shop.ui.adapter.ProductsAdapter
import ru.faaen.hackapp.features.shop.vm.ShopViewAction
import ru.faaen.hackapp.features.shop.vm.ShopViewModel
import ru.faaen.hackapp.features.shop.vm.ShopViewState
import javax.inject.Inject

@AndroidEntryPoint
class ShopFragment: BaseMvvmFragment<ShopViewState, ShopViewAction, ShopViewModel>(
    layoutResId = R.layout.fragment_shop
) {
    private val binding by viewBinding(FragmentShopBinding::bind)

    @Inject
    lateinit var viewModelFactory: ShopViewModel.Factory

    override val viewModel: ShopViewModel by viewModels {
        viewModelFactory.create()
    }

    private val itemsAdapter: ConcatAdapter get() = binding.productsRv.adapter as ConcatAdapter

    override fun setupUi() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            productsRv.adapter = ConcatAdapter(
                ProductsAdapter(),
                ShimmerAdapter(),
            )
        }
    }

    override fun processAction(action: ShopViewAction) {
        when (action) {
            is ShopViewAction.ShowSnackbarError -> {
                showSnackbarError(action.message)
            }
        }
    }

    override fun setupRender() {
        with(viewModel.uiState) {
            renderIn(viewLifecycle, { it.balance }, ::renderBalance)
            renderIn(viewLifecycle, { it.items }, ::renderItems)
        }
    }

    private fun renderBalance(balance: Int) {
        binding.balance.text = requireContext().getString(R.string.balance, balance)
    }

    private fun renderItems(items: List<BaseItem>) {
        itemsAdapter.setItems(items)
    }

    override fun onBackPressed(): Boolean {
        localRouter?.exit()
        return true
    }
}