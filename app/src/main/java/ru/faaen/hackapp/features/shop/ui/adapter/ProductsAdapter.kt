package ru.faaen.hackapp.features.shop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemProductBinding

class ProductsAdapter: BaseDelegateAdapter<ItemProductBinding, ProductItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemProductBinding = ItemProductBinding::inflate

    override fun BaseViewHolder<ItemProductBinding>.onBind(item: ProductItem) {
        with(binding) {
            imageIv.load(item.image)
            nameTv.text = item.name
            priceTv.text = context.getString(R.string.price, item.price)
        }
    }

    override fun areItemsTheSame(old: ProductItem, new: ProductItem): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: ProductItem, new: ProductItem): Boolean {
        return old == new
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is ProductItem
    }
}