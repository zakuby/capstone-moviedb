package com.dicoding.movie.data.remote

import com.dicoding.core.base.BaseRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val services: MovieServices
) : BaseRemoteDataSource(){

    suspend fun getMovies(page: Int, genres: String? = "", keywords: String? = "") =
        getResult {
            if (!keywords.isNullOrEmpty())
                services.searchMovies(page = page, keyword = keywords)
            else if (!genres.isNullOrEmpty())
                services.searchByGenres(page = page, genres = genres)
            else services.getPopularMovies(page = page)
        }

    fun getMovieGenres() = getFlowResult { services.getMovieGenres() }
}
