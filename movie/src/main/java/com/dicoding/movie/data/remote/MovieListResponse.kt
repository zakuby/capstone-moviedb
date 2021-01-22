package com.dicoding.movie.data.remote

import com.dicoding.core.data.local.models.Movie

data class MovieListResponse(
    val page: Int?,
    val results: List<Movie>?
)
