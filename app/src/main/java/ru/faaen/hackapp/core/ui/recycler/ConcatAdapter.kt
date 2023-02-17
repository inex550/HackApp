package ru.faaen.hackapp.core.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.faaen.hackapp.core.ui.recycler.diff.AdaptersDiffCallback

class ConcatAdapter(
    vararg adapters: DelegateAdapter
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _items: MutableList<BaseItem> = mutableListOf()
    private val adapters: MutableList<DelegateAdapter> = mutableListOf()

    fun getItems(): List<BaseItem> = _items
    fun getAdapters(): List<DelegateAdapter> = adapters

    fun setItems(items: List<BaseItem>) {
        val diffCallback = AdaptersDiffCallback(_items, items, adapters)
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)

        _items.clear()
        _items.addAll(items)
    }

    init {
        adapters.forEach { it.adapter = this }
        this.adapters.addAll(adapters)
    }

    fun updateItem(item: BaseItem, payload: Any? = null) {
        val itemIdx = _items.indexOf(item)
        if (itemIdx < 0) return
        notifyItemChanged(itemIdx, payload)
    }

    override fun getItemViewType(position: Int): Int {
        val item = _items[position]
        val viewType = adapters.indexOfFirst { it.isForItem(item) }
        if (viewType < 0) throw NoSuchElementException("adapter for $item at $position not found")
        return viewType
    }

    inline fun getAdapterViewType(cond: (DelegateAdapter) -> Boolean): Int {
        return getAdapters().indexOfFirst(cond)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return adapters[viewType].onCreateViewHolder(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        adapters[getItemViewType(position)].onBindViewHolder(holder, _items[position])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            adapters[getItemViewType(position)].onBindViewHolder(holder, _items[position], payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int = _items.size
}