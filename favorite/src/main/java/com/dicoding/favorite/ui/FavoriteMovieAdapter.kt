package com.dicoding.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.MovieEntity
import com.dicoding.core.domain.model.Movie
import com.dicoding.core.utils.formatDate
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.setProgressRating
import com.dicoding.favorite.databinding.ItemFavoriteMovieBinding

class FavoriteMovieAdapter(
    val onClick: (MovieEntity) -> Unit,
    val removeButton: (Int) -> Unit
) : BaseAdapter<MovieEntity, ItemFavoriteMovieBinding>() {

    fun isEmpty() = items.isNullOrEmpty()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieEntity, ItemFavoriteMovieBinding> {
        val binding = ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<MovieEntity, ItemFavoriteMovieBinding>,
        position: Int
    ) = holder.bind(getItem(position))

    inner class ViewHolder(binding: ItemFavoriteMovieBinding) : BaseViewHolder<MovieEntity, ItemFavoriteMovieBinding>(binding) {
        override fun bind(item: MovieEntity) {
            binding.apply {
                itemView.setOnClickListener { onClick(item) }
                movieImage.loadImageUrl(item.posterImage)
                progressBarRating.setProgressRating(item.rate)
                movieRating.text = item.rate
                movieTitle.text = item.title
                movieDate.formatDate(item.date)
                favoriteRemove.setOnClickListener { removeButton(item.id) }
            }
        }
    }
}
