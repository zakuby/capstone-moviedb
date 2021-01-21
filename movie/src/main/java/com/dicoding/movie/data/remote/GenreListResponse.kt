package com.dicoding.movie.data.remote

import com.dicoding.core.data.local.models.Genre

data class GenreListResponse(
    val genres: List<Genre>?
)