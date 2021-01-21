package com.dicoding.movie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.movie.data.local.Genre
import com.dicoding.movie.databinding.ListItemGenreFilterBinding

class GenreAdapter : BaseAdapter<Genre, ListItemGenreFilterBinding>() {

    override fun onBindViewHolder(
        holder: BaseViewHolder<Genre, ListItemGenreFilterBinding>,
        position: Int
    ) = holder.bind(getItem(position))



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Genre, ListItemGenreFilterBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemGenreFilterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    fun getCheckedGenres(): String {
        val checkedItems = items.filter { item -> item.isChecked }
        return checkedItems.joinToString(separator = ",") { it.id.toString() }
    }

    inner class ViewHolder(binding: ListItemGenreFilterBinding) : BaseViewHolder<Genre, ListItemGenreFilterBinding>(binding){
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