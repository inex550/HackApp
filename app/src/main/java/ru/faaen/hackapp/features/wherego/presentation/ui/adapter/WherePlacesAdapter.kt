package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemWherePlaceBinding

class WherePlacesAdapter(
    private val listener: Listener
): BaseDelegateAdapter<ItemWherePlaceBinding, WherePlaceItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemWherePlaceBinding = ItemWherePlaceBinding::inflate

    override fun BaseViewHolder<ItemWherePlaceBinding>.onBind(item: WherePlaceItem) {
        with(binding) {
            placeIv.load(item.image)
            nameTv.text = item.name
            cityTv.text = item.city
            countOptionsTv.text = context.getString(R.string.count_options, item.countOptions)
            seatCostTv.text = context.getString(R.string.seat_cost, item.seatCost)
            stayDurationTv.text = context.getString(R.string.stay_duration, item.stayDuration)

            root.setOnClickListener {
                listener.placeClicked(item)
            }

            likeIv.setOnClickListener {
                item.isLiked = !item.isLiked
                likeIv.setImageResource(if (item.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart)
            }
        }
    }

    override fun areItemsTheSame(old: WherePlaceItem, new: WherePlaceItem): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: WherePlaceItem, new: WherePlaceItem): Boolean {
        return old == new
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is WherePlaceItem
    }

    interface Listener {
        fun placeClicked(item: WherePlaceItem)
    }
}