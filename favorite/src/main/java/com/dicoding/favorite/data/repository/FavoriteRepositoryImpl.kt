package com.dicoding.favorite.data.repository

import com.dicoding.core.domain.model.Movie
import com.dicoding.core.domain.model.TvShow
import com.dicoding.favorite.data.local.FavoriteLocalDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteLocalDataSource
) : FavoriteRepository {
    override fun getFavoriteMovies() = localDataSource.getFavoriteMovies().map { entities ->
        val lists = ArrayList<Movie>()
        entities.map {
            val movie = Movie(
                id = it.id,
                title = it.title,
                date = it.date,
                description = it.description,
                rate = it.rate,
                backgroundImage = it.backgroundImage,
                posterImage = it.posterImage,
                genres = it.genres
            )
            lists.add(movie)
        }
        return@map lists
    }

    override fun getFavoriteTvShows() = localDataSource.getFavoriteTvShows().map { entities ->
        val lists = ArrayList<TvShow>()
        entities.map {
            val tvShow = TvShow(
                id = it.id,
                title = it.title,
                date = it.date,
                description = it.description,
                rate = it.rate,
                backgroundImage = it.backgroundImage,
                posterImage = it.posterImage,
                genres = it.genres
            )
            lists.add(tvShow)
        }
        return@map lists
    }
    override suspend fun removeTvShow(id: Int) = localDataSource.removeTvShow(id)
    override suspend fun removeMovie(id: Int) = localDataSource.removeMovie(id)
}
