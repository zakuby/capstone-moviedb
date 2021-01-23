package com.dicoding.detail.data.remote

import com.dicoding.detail.data.local.Detail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailServices {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<Detail>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(@Path("tv_id") tvId: Int): Response<Detail>

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int
    ): Response<CreditsResponse>

    @GET("tv/{id}/credits")
    suspend fun getTvShowCredits(
        @Path("id") id: Int
    ): Response<CreditsResponse>

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(
        @Path("id") id: Int
    ): Response<VideosResponse>

    @GET("tv/{id}/videos")
    suspend fun getTvShowVideos(
        @Path("id") id: Int
    ): Response<VideosResponse>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewResponse>

    @GET("tv/{id}/reviews")
    suspend fun getTvShowReviews(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewResponse>
}