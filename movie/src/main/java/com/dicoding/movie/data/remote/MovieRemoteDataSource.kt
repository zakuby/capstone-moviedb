package com.dicoding.movie.data.remote

import com.dicoding.core.data.remote.ApiCallHelper.getResult
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val services: MovieServices
) {

    suspend fun getMovies(page: Int, genres: String? = "", keywords: String? = "") =
        getResult {
            if (!keywords.isNullOrEmpty())
                services.searchMovies(page = page, keyword = keywords)
            else if (!genres.isNullOrEmpty())
                services.searchByGenres(page = page, genres = genres)
            else services.getPopularMovies(page = page)
        }

    suspend fun getMovieGenres() = getResult { services.getMovieGenres() }

}