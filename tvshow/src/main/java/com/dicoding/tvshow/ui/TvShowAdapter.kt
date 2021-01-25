package com.dicoding.tvshow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.domain.model.TvShow
import com.dicoding.core.utils.formatDate
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.setProgressRating
import com.dicoding.tvshow.data.TvShowPageDataSourceFactory.Companion.DIFF_CALLBACK
import com.dicoding.tvshow.databinding.ItemTvShowBinding

class TvShowAdapter(
    private val onClick: (TvShow) -> Unit
) : PagedListAdapter<TvShow, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemTvShowBinding) : BaseViewHolder<TvShow, ItemTvShowBinding>(binding) {
        override fun bind(item: TvShow) {
            binding.apply {
                itemView.setOnClickListener { onClick(item) }
                tvShowImage.loadImageUrl(item.posterImage)
                progressBarRating.setProgressRating(item.rate)
                tvShowRating.text = item.rate
                tvShowTitle.text = item.title
                tvShowDate.formatDate(item.date)
                tvShowDesc.text = item.description
            }
        }
    }
}
