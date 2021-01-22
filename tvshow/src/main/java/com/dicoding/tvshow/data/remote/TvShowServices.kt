package com.dicoding.tvshow.data.remote

import com.dicoding.core.data.local.models.TvShow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowServices {

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
}
