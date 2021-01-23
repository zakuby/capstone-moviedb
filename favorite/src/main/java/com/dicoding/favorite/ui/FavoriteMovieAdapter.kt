package com.dicoding.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dicoding.core.base.BaseAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.utils.formatDate
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.setProgressRating
import com.dicoding.favorite.databinding.ItemFavoriteMovieBinding

class FavoriteMovieAdapter(
    val onClick: (Movie) -> Unit,
    val removeButton: (Int) -> Unit
) : BaseAdapter<Movie, ItemFavoriteMovieBinding>() {

    fun isEmpty() = items.isNullOrEmpty()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemFavoriteMovieBinding> {
        val binding = ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<Movie, ItemFavoriteMovieBinding>,
        position: Int
    ) = holder.bind(getItem(position))

    inner class ViewHolder(binding: ItemFavoriteMovieBinding) : BaseViewHolder<Movie, ItemFavoriteMovieBinding>(binding) {
        override fun bind(item: Movie) {
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
