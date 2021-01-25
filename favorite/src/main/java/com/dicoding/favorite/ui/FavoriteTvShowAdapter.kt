package com.dicoding.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.TvShowEntity
import com.dicoding.core.domain.model.TvShow
import com.dicoding.core.utils.formatDate
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.setProgressRating
import com.dicoding.favorite.databinding.ItemFavoriteTvShowBinding

class FavoriteTvShowAdapter(
    val onClick: (TvShowEntity) -> Unit,
    val removeButton: (Int) -> Unit
) : BaseAdapter<TvShowEntity, ItemFavoriteTvShowBinding>() {

    fun isEmpty() = items.isNullOrEmpty()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TvShowEntity, ItemFavoriteTvShowBinding> {
        val binding = ItemFavoriteTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<TvShowEntity, ItemFavoriteTvShowBinding>,
        position: Int
    ) = holder.bind(getItem(position))

    inner class ViewHolder(binding: ItemFavoriteTvShowBinding) : BaseViewHolder<TvShowEntity, ItemFavoriteTvShowBinding>(binding) {
        override fun bind(item: TvShowEntity) {
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
