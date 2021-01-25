package com.dicoding.movie.data.remote

import com.dicoding.core.domain.model.Genre

data class GenreListResponse(
    val genres: List<Genre>?
)
