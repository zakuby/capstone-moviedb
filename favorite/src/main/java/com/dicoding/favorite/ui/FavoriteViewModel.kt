package com.dicoding.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.utils.SingleLiveEvent
import com.dicoding.detail.data.local.DetailType
import com.dicoding.favorite.data.FavoriteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    val movies: LiveData<List<Movie>> = repository.getFavoriteMovies()

    val tvShows: LiveData<List<TvShow>> = repository.getFavoriteTvShows()

    val removeMovieEvent = SingleLiveEvent<Unit>()

    fun removeFavorite(id: Int, type: DetailType) = viewModelScope.launch {
        if (type == DetailType.MOVIE)
            repository.removeMovie(id)
        else
            repository.removeTvShow(id)
        removeMovieEvent.call()
    }
}