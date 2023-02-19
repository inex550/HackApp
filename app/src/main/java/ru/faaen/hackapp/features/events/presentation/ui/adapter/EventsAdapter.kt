package ru.faaen.hackapp.features.events.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.ext.dateSdf
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemEventBinding
import java.util.*

class EventsAdapter(
    private val listener: Listener
): BaseDelegateAdapter<ItemEventBinding, EventsItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemEventBinding = ItemEventBinding::inflate

    override fun BaseViewHolder<ItemEventBinding>.onBind(item: EventsItem) {
        with(binding) {
            placeIv.load(item.image)
            nameTv.text = item.name
            genreTv.text = item.genre
            datesTv.text = context.getString(R.string.dates, item.startDate, item.endDate)

            friendsInfoLayout.friendsCountTv.text = resources.getQuantityString(
                R.plurals.friends_count, item.friendsInfo.friendsCount, item.friendsInfo.friendsCount
            )
            friendsInfoLayout.root.isVisible = item.friendsInfo.friendsCount > 0

            friendsInfoLayout.avatar1Iv.isGone = item.friendsInfo.avatar1 == null
            friendsInfoLayout.avatar2Iv.isGone = item.friendsInfo.avatar2 == null
            friendsInfoLayout.avatar3Iv.isGone = item.friendsInfo.avatar3 == null

            friendsInfoLayout.avatar1Iv.load(item.friendsInfo.avatar1)
            friendsInfoLayout.avatar2Iv.load(item.friendsInfo.avatar2)
            friendsInfoLayout.avatar3Iv.load(item.friendsInfo.avatar3)

            friendsInfoLayout.likeIv.setImageResource(
                if (item.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart
            )

            friendsInfoLayout.likeIv.setOnClickListener {
                item.isLiked = !item.isLiked
                friendsInfoLayout.likeIv.setImageResource(
                    if (item.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart
                )
            }

            root.setOnClickListener {
                listener.onEventClicked(item)
            }
        }
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is EventsItem
    }

    interface Listener {
        fun onEventClicked(item: EventsItem)
    }
}