package com.dicoding.core.data.remote.response

import com.dicoding.core.data.local.models.TvShow

data class TvShowListResponse(
    val page: Int?,
    val results: List<TvShow>?
)