package com.dicoding.detail.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailServices {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<DetailResponse>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(@Path("tv_id") tvId: Int): Response<DetailResponse>

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int
    ): Response<CastListResponse>

    @GET("tv/{id}/credits")
    suspend fun getTvShowCredits(
        @Path("id") id: Int
    ): Response<CastListResponse>

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(
        @Path("id") id: Int
    ): Response<VideoListResponse>

    @GET("tv/{id}/videos")
    suspend fun getTvShowVideos(
        @Path("id") id: Int
    ): Response<VideoListResponse>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewListResponse>

    @GET("tv/{id}/reviews")
    suspend fun getTvShowReviews(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewListResponse>
}