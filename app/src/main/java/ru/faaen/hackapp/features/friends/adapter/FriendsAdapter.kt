package ru.faaen.hackapp.features.friends.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemFriendsBinding
import ru.faaen.hackapp.databinding.ItemLifeProfileBinding
import ru.faaen.hackapp.features.friends.data.FriendsItem
import ru.faaen.hackapp.features.profile.data.LifeProfileItem

class FriendsAdapter(): BaseDelegateAdapter<ItemFriendsBinding, FriendsItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemFriendsBinding = ItemFriendsBinding::inflate

    override fun BaseViewHolder<ItemFriendsBinding>.onBind(item: FriendsItem) {
        with(binding) {
            imFriends.load(item.image)
            tvTitleFriends.text = item.name
            tvCityFriends.text = item.city
            tvDescFriends.text = item.desc
        }
    }

    override fun areItemsTheSame(old: FriendsItem, new: FriendsItem): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: FriendsItem, new: FriendsItem): Boolean {
        return old == new
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is FriendsItem
    }
}

