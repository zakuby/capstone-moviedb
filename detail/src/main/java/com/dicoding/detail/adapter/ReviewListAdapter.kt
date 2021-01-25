package com.dicoding.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.domain.model.Review
import com.dicoding.detail.adapter.ReviewPageDataSourceFactory.Companion.DIFF_CALLBACK
import com.dicoding.detail.databinding.ItemReviewBinding

class ReviewListAdapter(
    val onClick: (Review) -> Unit
) : PagedListAdapter<Review, ReviewListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}