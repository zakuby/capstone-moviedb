package com.dicoding.core.data.remote

import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.data.remote.response.DetailVideoResponse
import com.dicoding.core.data.remote.response.MovieCreditsResponse
import com.dicoding.core.data.remote.response.ReviewResponse
import com.dicoding.core.data.remote.response.TvShowListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbServices {


    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(@Path("tv_id") tvId: Int): Response<TvShow>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowListResponse>

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("query") keyword: String
    ): Response<TvShowListResponse>

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int
    ): Response<MovieCreditsResponse>

    @GET("tv/{id}/credits")
    suspend fun getTvShowCredits(
        @Path("id") id: Int
    ): Response<MovieCreditsResponse>

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(
        @Path("id") id: Int
    ): Response<DetailVideoResponse>

    @GET("tv/{id}/videos")
    suspend fun getTvShowVideos(
        @Path("id") id: Int
    ): Response<DetailVideoResponse>

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