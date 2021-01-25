package com.dicoding.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.domain.model.Video
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.detail.databinding.ItemVideoBinding

class VideoListAdapter(val onClick: (String) -> Unit) : BaseAdapter<Video, ItemVideoBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Video, ItemVideoBinding> {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemVideoBinding) : BaseViewHolder<Video, ItemVideoBinding>(binding) {
        override fun bind(item: Video) {
            binding.apply {
                videoImage.loadImageUrl(item.youtubeThumbnail, false)
                btnPlay.setOnClickListener { onClick(item.key ?: "https://www.youtube.com/watch?v=dQw4w9WgXcQ") }
                videoName.text = item.name
            }
        }
    }
}
