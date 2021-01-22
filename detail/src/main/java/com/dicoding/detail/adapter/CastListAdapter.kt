package com.dicoding.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.Cast
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.detail.databinding.ItemCastBinding

class CastListAdapter : BaseAdapter<Cast, ItemCastBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Cast, ItemCastBinding> {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemCastBinding) : BaseViewHolder<Cast, ItemCastBinding>(binding){
        override fun bind(item: Cast) {
            binding.apply {
                castImage.loadImageUrl(item.profileImage)
                castName.text = item.name
                castCharacter.text = item.character
            }
        }

    }
}