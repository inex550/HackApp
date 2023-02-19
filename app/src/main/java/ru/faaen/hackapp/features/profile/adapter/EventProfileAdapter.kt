package ru.faaen.hackapp.features.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemEventProfileBinding
import ru.faaen.hackapp.features.profile.data.EventProfileItem

class EventProfileAdapter(): BaseDelegateAdapter<ItemEventProfileBinding, EventProfileItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemEventProfileBinding = ItemEventProfileBinding::inflate

    override fun BaseViewHolder<ItemEventProfileBinding>.onBind(item: EventProfileItem) {
        with(binding) {
            tvTitleProfile.text = item.title
            tvDataProfile.text = item.data
            tvCountPersonProfile.text = item.countPerson.toString()
            tvSumProfile.text= item.sum
            if(item.id.toInt() % 2 == 0) imBonusProfile.setImageResource(R.drawable.ic_bonus_7) else imBonusProfile.setImageResource(R.drawable.ic_bonus_5)
        }
    }

    override fun areItemsTheSame(old: EventProfileItem, new: EventProfileItem): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: EventProfileItem, new: EventProfileItem): Boolean {
        return old == new
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is EventProfileItem
    }
}

