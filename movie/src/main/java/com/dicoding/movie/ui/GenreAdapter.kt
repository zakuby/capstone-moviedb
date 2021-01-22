package com.dicoding.movie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.Genre
import com.dicoding.movie.databinding.ItemGenreFilterBinding

class GenreAdapter : BaseAdapter<Genre, ItemGenreFilterBinding>() {

    override fun onBindViewHolder(
        holder: BaseViewHolder<Genre, ItemGenreFilterBinding>,
        position: Int
    ) = holder.bind(getItem(position))

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Genre, ItemGenreFilterBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreFilterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    fun getCheckedGenres(): String {
        val checkedItems = items.filter { item -> item.isChecked }
        return checkedItems.joinToString(separator = ",") { it.id.toString() }
    }

    inner class ViewHolder(binding: ItemGenreFilterBinding) : BaseViewHolder<Genre, ItemGenreFilterBinding>(binding) {
        override fun bind(item: Genre) {
            binding.apply {
                genre.setOnClickListener {
                    item.setCheck()
                    notifyDataSetChanged()
                }
                genre.isChecked = item.isChecked
                genre.text = item.name
            }
        }
    }
}
