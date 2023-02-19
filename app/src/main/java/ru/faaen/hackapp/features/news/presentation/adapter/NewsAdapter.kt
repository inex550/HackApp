package ru.faaen.hackapp.features.news.presentation.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.ext.dp
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemEventBinding
import ru.faaen.hackapp.databinding.ItemNewsBinding

class NewsAdapter(
    private val listener: Listener
): BaseDelegateAdapter<ItemNewsBinding, NewsItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemNewsBinding = ItemNewsBinding::inflate

    override fun BaseViewHolder<ItemNewsBinding>.onBind(item: NewsItem) {
        with(binding) {
            placeIv.load(item.image)
            nameTv.text = item.name

            tagsGroup.removeAllViews()
            for (tag in item.tags) {
                makeChip(tag)
            }

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

    private fun ItemNewsBinding.makeChip(tag: String) {
        val chip = LayoutInflater.from(root.context)
            .inflate(R.layout.layout_tag_chip, tagsGroup, false) as Chip
        tagsGroup.addView(chip)
        chip.text = tag
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is NewsItem
    }

    interface Listener {
        fun onEventClicked(item: NewsItem)
    }

    companion object {
        private const val CHIP_HEIGHT = 40
    }
}