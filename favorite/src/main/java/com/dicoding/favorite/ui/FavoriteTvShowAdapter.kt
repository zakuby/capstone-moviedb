package com.dicoding.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.utils.formatDate
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.setProgressRating
import com.dicoding.favorite.databinding.ItemFavoriteTvShowBinding

class FavoriteTvShowAdapter(
    val onClick: (TvShow) -> Unit,
    val removeButton: (Int) -> Unit
) : BaseAdapter<TvShow, ItemFavoriteTvShowBinding>() {

    fun isEmpty() = items.isNullOrEmpty()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TvShow, ItemFavoriteTvShowBinding> {
        val binding = ItemFavoriteTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<TvShow, ItemFavoriteTvShowBinding>,
        position: Int
    ) = holder.bind(getItem(position))

    inner class ViewHolder(binding: ItemFavoriteTvShowBinding) : BaseViewHolder<TvShow, ItemFavoriteTvShowBinding>(binding) {
        override fun bind(item: TvShow) {
            binding.apply {
                itemView.setOnClickListener { onClick(item) }
                tvShowImage.loadImageUrl(item.posterImage)
                progressBarRating.setProgressRating(item.rate)
                tvShowRating.text = item.rate
                tvShowTitle.text = item.title
                tvShowDate.formatDate(item.date)
                favoriteRemove.setOnClickListener { removeButton(item.id) }
            }
        }
    }
}
