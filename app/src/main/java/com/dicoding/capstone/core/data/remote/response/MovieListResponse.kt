package com.dicoding.capstone.core.data.remote.response

import com.dicoding.capstone.core.data.local.models.Movie

data class MovieListResponse(
    val page: Int?,
    val results: List<Movie>?
)