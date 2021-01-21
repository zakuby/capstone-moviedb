package com.dicoding.tvshow.data.remote

import com.dicoding.core.data.remote.ApiCallHelper.getResult
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(
    private val services: TvShowServices
) {

    suspend fun getTvShows(page: Int, keywords: String? = "") =
        getResult {
            if (!keywords.isNullOrEmpty())
                services.searchTvShows(page = page, keyword = keywords)
            else services.getTopRatedTvShows(page = page)
        }

}