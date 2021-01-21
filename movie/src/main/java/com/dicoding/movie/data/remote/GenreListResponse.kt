package com.dicoding.movie.data.remote

import com.dicoding.movie.data.local.Genre

data class GenreListResponse(
    val genres: List<Genre>?
)