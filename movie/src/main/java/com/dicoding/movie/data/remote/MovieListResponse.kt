package com.dicoding.movie.data.remote

data class MovieListResponse(
    val page: Int?,
    val results: List<MovieResponse>?
)
