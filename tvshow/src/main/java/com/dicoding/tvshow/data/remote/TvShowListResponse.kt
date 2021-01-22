package com.dicoding.tvshow.data.remote

import com.dicoding.core.data.local.models.TvShow

data class TvShowListResponse(
    val page: Int?,
    val results: List<TvShow>?
)
