package ru.faaen.hackapp.features.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemLifeProfileBinding
import ru.faaen.hackapp.features.profile.data.LifeProfileItem

class LifeProfileAdapter(): BaseDelegateAdapter<ItemLifeProfileBinding, LifeProfileItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemLifeProfileBinding = ItemLifeProfileBinding::inflate

    override fun BaseViewHolder<ItemLifeProfileBinding>.onBind(item: LifeProfileItem) {
        with(binding) {
            tvTitleLifeProfile.text = item.title
            tvDataLifeProfile.text = item.data
            tvCountPersonLifeProfile.text = item.countPerson.toString()
            tvSumLifeProfile.text= item.sum
            if(item.id.toInt() % 2 == 0) imBonusLifeProfile.setImageResource(R.drawable.ic_bonus_7) else imBonusLifeProfile.setImageResource(R.drawable.ic_bonus_5)
        }
    }

    override fun areItemsTheSame(old: LifeProfileItem, new: LifeProfileItem): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: LifeProfileItem, new: LifeProfileItem): Boolean {
        return old == new
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is LifeProfileItem
    }
}

