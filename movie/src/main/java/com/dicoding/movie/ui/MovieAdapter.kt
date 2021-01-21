package com.dicoding.movie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.dicoding.core.base.BaseViewHolder
import com.dicoding.movie.data.local.Movie
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.setProgressRating
import com.dicoding.movie.databinding.ItemMovieBinding

class MovieAdapter(private val onClick: (Movie) -> Unit) : PagedListAdapter<Movie, MovieAdapter.ViewHolder>(
    Movie.DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(binding: ItemMovieBinding) : BaseViewHolder<Movie, ItemMovieBinding>(binding){
        override fun bind(item: Movie) {
            binding.apply {
                itemView.setOnClickListener { onClick(item) }
                movieImage.loadImageUrl(item.posterImage)
                progressBarRating.setProgressRating(item.rate)
                movieRating.text = item.rate
                movieTitle.text = item.title
                movieDate.text = item.date
                movieDesc.text  = item.description
            }
        }

    }

}