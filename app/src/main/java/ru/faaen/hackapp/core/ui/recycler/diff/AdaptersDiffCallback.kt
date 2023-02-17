package ru.faaen.hackapp.core.ui.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.DelegateAdapter

class AdaptersDiffCallback(
    private val oldItems: List<BaseItem>,
    private val newItems: List<BaseItem>,
    private val adapters: List<DelegateAdapter>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        adapters.firstOrNull { it.isForItem(oldItem) && it.isForItem(newItem) }?.let{
            return it.checkItemsTheSame(oldItem, newItem)
        }
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        adapters.firstOrNull { it.isForItem(oldItem) && it.isForItem(newItem) }?.let {
            return it.checkContentsTheSame(oldItem, newItem)
        }
        return false
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        adapters.firstOrNull { it.isForItem(oldItem) && it.isForItem(newItem) }?.let {
            return it.getPayload(oldItem, newItem)
        }
        return null
    }
}