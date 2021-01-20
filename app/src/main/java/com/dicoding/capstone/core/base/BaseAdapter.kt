package com.dicoding.capstone.core.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

private class BaseAdapter<T, VH : RecyclerView.ViewHolder>(
    private val viewHolderCreator: (ViewGroup, Int) -> VH,
    private val viewHolderBinder: (holder: VH, item: T, position: Int) -> Unit,
    private val viewTypeFunction: ((T) -> Int)? = null
) : RecyclerView.Adapter<VH>() {

    private var items: List<T> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = viewHolderCreator(parent, viewType)

    override fun getItemViewType(position: Int): Int =
        viewTypeFunction?.invoke(items[position]) ?: super.getItemViewType(position)

    override fun onBindViewHolder(holder: VH, position: Int) = viewHolderBinder(holder, items[position], position)

    override fun getItemCount(): Int = items.size

}

fun <T, VH : RecyclerView.ViewHolder> adapterOf(
   viewHolderCreator: (ViewGroup, Int) -> VH,
   viewHolderBinder: (holder: VH, item: T, position: Int) -> Unit,
   viewTypeFunction: ((T) -> Int)? = null
): RecyclerView.Adapter<VH> = BaseAdapter(viewHolderCreator, viewHolderBinder, viewTypeFunction)