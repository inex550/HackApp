package ru.faaen.hackapp.core.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.faaen.hackapp.core.ui.recycler.BaseItem
import ru.faaen.hackapp.core.ui.recycler.ConcatAdapter

interface DelegateAdapter {

    var adapter: ConcatAdapter

    fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: BaseItem)

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: BaseItem, payloads: List<Any>)

    fun checkItemsTheSame(old: BaseItem, new: BaseItem): Boolean

    fun checkContentsTheSame(old: BaseItem, new: BaseItem): Boolean

    fun getPayload(old: BaseItem, new: BaseItem): Any?

    fun isForItem(item: BaseItem): Boolean
}