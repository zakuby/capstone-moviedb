package com.dicoding.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.Genre
import com.dicoding.detail.databinding.ItemGenreBinding

class GenreListAdapter : BaseAdapter<Genre, ItemGenreBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Genre, ItemGenreBinding> {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemGenreBinding) : BaseViewHolder<Genre, ItemGenreBinding>(binding){
        override fun bind(item: Genre) {
            binding.genreName.text = item.name
        }

    }
}