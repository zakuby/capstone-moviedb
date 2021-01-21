package com.dicoding.movie.data.remote

import com.dicoding.movie.data.local.Movie

data class MovieListResponse(
    val page: Int?,
    val results: List<Movie>?
)