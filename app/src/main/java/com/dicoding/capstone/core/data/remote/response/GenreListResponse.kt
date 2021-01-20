package com.dicoding.capstone.core.data.remote.response

import com.dicoding.capstone.core.data.local.models.Genre

data class GenreListResponse(
    val genres: List<Genre>?
)