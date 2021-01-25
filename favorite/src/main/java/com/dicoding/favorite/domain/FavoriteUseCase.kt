package com.dicoding.favorite.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Movie
import com.dicoding.core.domain.model.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteTvShows(): Flow<List<TvShow>>
    suspend fun removeTvShow(id: Int)
    suspend fun removeMovie(id: Int)
}