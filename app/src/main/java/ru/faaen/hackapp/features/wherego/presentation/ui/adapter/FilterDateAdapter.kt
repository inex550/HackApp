package ru.faaen.hackapp.features.wherego.presentation.ui.adapter

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import com.google.android.material.datepicker.MaterialDatePicker
import ru.faaen.hackapp.R
import ru.faaen.hackapp.core.common.ext.dateSdf
import ru.faaen.hackapp.core.ui.recycler.BaseDelegateAdapter
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.BaseViewHolder
import ru.faaen.hackapp.databinding.ItemFilterDateBinding
import java.util.*

class FilterDateAdapter(
    private val listener: Listener
): BaseDelegateAdapter<ItemFilterDateBinding, FilterDateItem>() {

    override val viewBindingInflater: (
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ) -> ItemFilterDateBinding = ItemFilterDateBinding::inflate

    override fun BaseViewHolder<ItemFilterDateBinding>.onBind(item: FilterDateItem) {
        binding.updateItem(item)

        binding.filterChip.setOnClickListener {
            val calendar = Calendar.getInstance()

            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    calendar.clear()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    item.date = calendar.time.time
                    binding.updateItem(item)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
            ).show()
        }
    }

    private fun ItemFilterDateBinding.updateItem(item: FilterDateItem) {
        filterChip.text = item.date?.let { dateSdf.format(Date(it)) }
            ?: root.context.getString(R.string.date)
    }

    override fun isForItem(item: BaseItem): Boolean {
        return item is FilterDateItem
    }

    interface Listener {
        fun onNewDateSelected(item: FilterDateItem)
    }
}