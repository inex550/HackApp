package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.ui.recycler.BaseAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemFilterOptionBinding
import ru.faaen.hackapp.features.wherego.data.models.FilterOption

class FilterOptionsAdapter(
    private val listener: Listener,
    private val selectedOption: FilterOption?
): BaseAdapter<FilterOption, ItemFilterOptionBinding>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemFilterOptionBinding = ItemFilterOptionBinding::inflate

    override fun BaseViewHolder<ItemFilterOptionBinding>.onBind(item: FilterOption) {
        with(binding) {
            titleTv.text = item.name
            checkIv.isVisible = item == selectedOption

            root.setOnClickListener {
                if (item == selectedOption) {
                    listener.onNewOptionSelected(null)
                } else {
                    listener.onNewOptionSelected(item)
                }
            }
        }
    }

    interface Listener {
        fun onNewOptionSelected(option: FilterOption?)
    }
}