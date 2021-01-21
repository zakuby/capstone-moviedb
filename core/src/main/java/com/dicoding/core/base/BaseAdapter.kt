package com.dicoding.core.base

import android.view.View
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB: ViewBinding>(val onClick: (T) -> Unit = {}) : RecyclerView.Adapter<BaseViewHolder<T, VB>>() {

    private var _items: List<T> = listOf()

    val items: List<T> get() = _items

    fun submitList(items: List<T>){
        this._items = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = items[position]

    override fun getItemCount(): Int = items.size

}

abstract class BasePagedListAdapter<T, VB: ViewBinding>(
    val onClick: (T) -> Unit = {},
    diffUtil: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BaseViewHolder<T, VB>>(diffUtil)

abstract class BaseViewHolder<T, VB: ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root){
    abstract fun bind(item: T)
}