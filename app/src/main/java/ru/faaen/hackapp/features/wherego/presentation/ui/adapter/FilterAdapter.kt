package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import ru.faaen.hackapp.core.ui.ext.dp
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemFilterBinding
import ru.faaen.hackapp.databinding.PopupOptionsBinding
import ru.faaen.hackapp.features.wherego.data.models.FilterOption
import ru.faaen.hackapp.features.wherego.data.models.Filters

class FilterAdapter(
    private val listener: Listener,
): BaseDelegateAdapter<ItemFilterBinding, FilterItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemFilterBinding = ItemFilterBinding::inflate

    override fun BaseViewHolder<ItemFilterBinding>.onBind(item: FilterItem) {
        with(binding) {
            filterChip.text = item.option?.name ?: item.filter.title

            filterChip.setOnClickListener {
                val popupBinding = PopupOptionsBinding.inflate(LayoutInflater.from(context))

                val popup = PopupWindow(context)
                popup.contentView = popupBinding.root

                popup.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                popup.isOutsideTouchable = true

                val optionSelectedListener = object : FilterOptionsAdapter.Listener {
                    override fun onNewOptionSelected(option: FilterOption?) {
                        updateWithNewOption(item, option)
                        popup.dismiss()
                    }
                }

                val adapter = FilterOptionsAdapter(optionSelectedListener, item.option)
                popupBinding.optionsRv.adapter = adapter

                adapter.setItems(item.filter.options)

                popup.showAsDropDown(filterChip)
            }
        }
    }

    private fun ItemFilterBinding.updateWithNewOption(item: FilterItem, option: FilterOption?) {
        item.option = option
        filterChip.text = option?.name ?: item.filter.title
    }

    override fun areItemsTheSame(old: FilterItem, new: FilterItem): Boolean {
        return old.filter == new.filter
    }

    override fun areContentsTheSame(old: FilterItem, new: FilterItem): Boolean {
        return old.option == new.option
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is FilterItem
    }

    interface Listener {
        fun onNewFilterOptionSelected(filter: Filters, option: FilterOption?)
    }
}