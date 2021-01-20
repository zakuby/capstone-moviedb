package com.dicoding.capstone.core.data.remote.response

import com.dicoding.capstone.core.data.local.models.TvShow

data class TvShowListResponse(
    val page: Int?,
    val results: List<TvShow>?
)