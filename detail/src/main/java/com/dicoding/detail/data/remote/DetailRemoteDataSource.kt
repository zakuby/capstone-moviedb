package com.dicoding.detail.data.remote

import com.dicoding.core.base.BaseRemoteDataSource
import com.dicoding.core.domain.model.DetailType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRemoteDataSource @Inject constructor(
    private val services: DetailServices
) : BaseRemoteDataSource(){

    fun getDetail(id: Int, type: DetailType) = getFlowResult {
        if (type == DetailType.MOVIE) services.getMovieDetail(id) else services.getTvShowDetail(id)
    }

    fun getDetailCasts(id: Int, type: DetailType) = getFlowResult {
        if (type == DetailType.MOVIE) services.getMovieCredits(id) else services.getTvShowCredits(id)
    }

    fun getDetailVideos(id: Int, type: DetailType) = getFlowResult {
        if (type == DetailType.MOVIE) services.getMovieVideos(id) else services.getTvShowVideos(id)
    }

    suspend fun getDetailReviews(id: Int, type: DetailType, page: Int) = getResult {
        if (type == DetailType.MOVIE) services.getMovieReviews(id, page) else services.getTvShowReviews(id, page)
    }

}