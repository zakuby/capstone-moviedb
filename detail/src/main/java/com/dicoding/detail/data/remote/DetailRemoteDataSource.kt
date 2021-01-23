package com.dicoding.detail.data.remote

import com.dicoding.core.data.remote.ApiCallHelper
import com.dicoding.detail.data.DetailType
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
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

    suspend fun getDetailReviews(id: Int, type: DetailType, page: Int) = ApiCallHelper.getResult {
        if (type == DetailType.MOVIE) services.getMovieReviews(id, page) else services.getTvShowReviews(id, page)
    }

}