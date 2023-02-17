package ru.faaen.hackapp.core.ui.recycler.diff

interface DiffAdapter<T> {

    fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    fun getPayload(oldItem: T): Any?
}