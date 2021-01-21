package com.dicoding.tvshow.data.remote

import com.dicoding.tvshow.data.local.TvShow

data class TvShowListResponse(
    val page: Int?,
    val results: List<com.dicoding.tvshow.data.local.TvShow>?
)