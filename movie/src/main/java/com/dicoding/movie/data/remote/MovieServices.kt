package com.dicoding.movie.data.remote

import com.dicoding.core.data.local.models.Movie
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServices {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<Movie>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponse>

    @GET("discover/movie")
    suspend fun getTodayReleaseMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("primary_release_date.gte") dateGte: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
            Date()
        ),
        @Query("primary_release_date.lte") dateLte: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
            Date()
        )
    ): Response<MovieListResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("query") keyword: String
    ): Response<MovieListResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(@Query("language") lang: String = "en-US"): Response<GenreListResponse>

    @GET("discover/movie")
    suspend fun searchByGenres(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("with_genres") genres: String
    ): Response<MovieListResponse>
}
