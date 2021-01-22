package com.dicoding.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.utils.SingleLiveEvent
import com.dicoding.favorite.data.FavoriteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    val movies: LiveData<List<Movie>> = repository.getFavoriteMovies()
    val removeMovieEvent = SingleLiveEvent<Unit>()

    fun removeMovie(id: Int) = viewModelScope.launch {
        repository.removeMovie(id)
    }

    val tvShows: LiveData<List<TvShow>> = repository.getFavoriteTvShows()
    val removeTvShowEvent = SingleLiveEvent<Unit>()

    fun removeTvShow(id: Int) = viewModelScope.launch {
        repository.removeTvShow(id)
    }
}