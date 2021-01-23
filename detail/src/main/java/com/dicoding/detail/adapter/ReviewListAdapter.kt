package com.dicoding.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.Review
import com.dicoding.detail.databinding.ItemReviewBinding

class ReviewListAdapter(val onClick: (Review) -> Unit) : BaseAdapter<Review, ItemReviewBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Review, ItemReviewBinding> {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemReviewBinding) : BaseViewHolder<Review, ItemReviewBinding>(binding){
        override fun bind(item: Review) {
            binding.apply {
                itemView.setOnClickListener { onClick(item) }
                reviewName.text = item.name
                reviewContent.text = item.content
            }
        }

    }
}