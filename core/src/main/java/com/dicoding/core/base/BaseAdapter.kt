package com.dicoding.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding> : RecyclerView.Adapter<BaseViewHolder<T, VB>>() {

    private var _items: List<T> = listOf()

    val items: List<T> get() = _items

    fun submitList(items: List<T>) {
        this._items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) = holder.bind(getItem(position))

    fun getItem(position: Int) = items[position]

    override fun getItemCount(): Int = items.size
}

abstract class BaseViewHolder<T, VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}
