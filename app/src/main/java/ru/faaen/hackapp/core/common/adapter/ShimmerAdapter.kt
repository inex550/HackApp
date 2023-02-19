package ru.faaen.hackapp.core.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemShimmerBinding

class ShimmerAdapter: BaseDelegateAdapter<ItemShimmerBinding, ShimmerItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemShimmerBinding = ItemShimmerBinding::inflate

    override fun BaseViewHolder<ItemShimmerBinding>.onCreate() {
        binding.root.post {
            binding.root.updateLayoutParams {
                height = binding.root.width
            }
        }
    }

    override fun BaseViewHolder<ItemShimmerBinding>.onBind(item: ShimmerItem) {
        binding.root.startShimmer()
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is ShimmerItem
    }
}