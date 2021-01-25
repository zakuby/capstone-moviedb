package com.dicoding.favorite.data.repository

import com.dicoding.core.domain.model.Movie
import com.dicoding.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteTvShows(): Flow<List<TvShow>>
    suspend fun removeTvShow(id: Int)
    suspend fun removeMovie(id: Int)
}