package com.dicoding.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.core.data.local.models.MovieEntity
import com.dicoding.core.data.local.models.TvShowEntity
import com.dicoding.core.domain.model.Movie
import com.dicoding.core.domain.model.TvShow
import com.dicoding.core.utils.SingleLiveEvent
import com.dicoding.detail.data.local.DetailType
import com.dicoding.favorite.data.repository.FavoriteRepositoryImpl
import com.dicoding.favorite.domain.FavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val useCase: FavoriteUseCase
) : ViewModel() {

    val movies: LiveData<List<Movie>> = useCase.getFavoriteMovies().asLiveData(viewModelScope.coroutineContext)

    val tvShows: LiveData<List<TvShow>> = useCase.getFavoriteTvShows().asLiveData(viewModelScope.coroutineContext)

    val removeMovieEvent = SingleLiveEvent<Unit>()

    fun removeFavorite(id: Int, type: DetailType) = viewModelScope.launch {
        if (type == DetailType.MOVIE)
            useCase.removeMovie(id)
        else
            useCase.removeTvShow(id)
        removeMovieEvent.call()
    }
}