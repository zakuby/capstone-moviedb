package com.dicoding.favorite.domain

import com.dicoding.core.domain.model.Movie
import com.dicoding.core.domain.model.TvShow
import com.dicoding.favorite.data.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val repository: FavoriteRepositoryImpl
): FavoriteUseCase {
    override fun getFavoriteMovies(): Flow<List<Movie>> = repository.getFavoriteMovies()

    override fun getFavoriteTvShows(): Flow<List<TvShow>> = repository.getFavoriteTvShows()

    override suspend fun removeMovie(id: Int) = repository.removeMovie(id)

    override suspend fun removeTvShow(id: Int) = repository.removeTvShow(id)

}