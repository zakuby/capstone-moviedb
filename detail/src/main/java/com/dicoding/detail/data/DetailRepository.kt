package com.dicoding.detail.data

import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import com.dicoding.core.data.remote.ApiCallHelper
import com.dicoding.detail.data.remote.DetailServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val services: DetailServices
) {
    suspend fun getDetail(id: Int, type: DetailType) = ApiCallHelper.getResult {
        if (type == DetailType.MOVIE) services.getMovieDetail(id) else services.getTvShowDetail(id)
    }

    suspend fun getDetailCasts(id: Int, type: DetailType) = ApiCallHelper.getResult {
        if (type == DetailType.MOVIE) services.getMovieCredits(id) else services.getTvShowCredits(id)
    }

    suspend fun getDetailVideos(id: Int, type: DetailType) = ApiCallHelper.getResult {
        if (type == DetailType.MOVIE) services.getMovieVideos(id) else services.getTvShowVideos(id)
    }
}