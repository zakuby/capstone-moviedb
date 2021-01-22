package com.dicoding.favorite.data

import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val movieDataSource: MovieDao,
    private val tvShowDataSource: TvShowDao
) {
    fun getFavoriteMovies() = movieDataSource.selectAll()

    suspend fun removeMovie(id: Int) = movieDataSource.deleteById(id)

    fun getFavoriteTvShows() = tvShowDataSource.selectAll()

    suspend fun removeTvShow(id: Int) = tvShowDataSource.deleteById(id)
}
