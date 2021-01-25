package com.dicoding.tvshow.data.remote

data class TvShowListResponse(
    val page: Int?,
    val results: List<TvShowResponse>?
)
