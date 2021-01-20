package com.dicoding.capstone.core.data.remote.service

import com.dicoding.capstone.core.data.local.models.Movie
import com.dicoding.capstone.core.data.local.models.TvShow
import com.dicoding.capstone.core.data.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface TheMovieDbServices {

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Response<Movie>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponse>

    @GET("discover/movie")
    fun getTodayReleaseMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("primary_release_date.gte") dateGte: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
        @Query("primary_release_date.lte") dateLte: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    ): Response<MovieListResponse>

    @GET("search/movie")
    fun searchMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("query") keyword: String
    ): Response<MovieListResponse>

    @GET("genre/movie/list")
    fun getMovieGenres(@Query("language") lang: String = "en-US"): Response<GenreListResponse>

    @GET("discover/movie")
    fun searchByGenres(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("with_genres") genres: String
    ): Response<MovieListResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(@Path("tv_id") tvId: Int): Response<TvShow>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowListResponse>

    @GET("search/tv")
    fun searchTvShows(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("query") keyword: String
    ): Response<TvShowListResponse>

    @GET("movie/{id}/credits")
    fun getMovieCredits(
        @Path("id") id: Int
    ): Response<MovieCreditsResponse>

    @GET("tv/{id}/credits")
    fun getTvShowCredits(
        @Path("id") id: Int
    ): Response<MovieCreditsResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(
        @Path("id") id: Int
    ): Response<DetailVideoResponse>

    @GET("tv/{id}/videos")
    fun getTvShowVideos(
        @Path("id") id: Int
    ): Response<DetailVideoResponse>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewResponse>

    @GET("tv/{id}/reviews")
    fun getTvShowReviews(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): Response<ReviewResponse>
}