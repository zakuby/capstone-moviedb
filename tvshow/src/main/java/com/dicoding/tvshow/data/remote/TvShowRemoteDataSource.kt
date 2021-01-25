package com.dicoding.tvshow.data.remote

import com.dicoding.core.base.BaseRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRemoteDataSource @Inject constructor(
    private val services: TvShowServices
) : BaseRemoteDataSource(){

    suspend fun getTvShows(page: Int, keywords: String? = "") =
        getResult {
            if (!keywords.isNullOrEmpty())
                services.searchTvShows(page = page, keyword = keywords)
            else services.getTopRatedTvShows(page = page)
        }
}
