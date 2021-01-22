package com.dicoding.movie.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.FilterType
import com.dicoding.core.data.local.models.Genre
import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.movie.data.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val filterData = MutableLiveData<String>().apply {
        postValue("")
    }

    val resultPaging = MutableLiveData<ResultPaging>()

    val movies: LiveData<PagedList<Movie>>
        get() = Transformations.switchMap(filterData) { filter ->
            when {
                filter.contains("FILTER_BY_KEYWORD") -> {
                    repository.getMovies(
                        viewModelScope,
                        keywords = filter.replace("FILTER_BY_KEYWORD", ""),
                        resultPaging = resultPaging
                    )
                }
                filter.contains("FILTER_BY_GENRE") -> {
                    repository.getMovies(
                        viewModelScope,
                        genres = filter.replace("FILTER_BY_GENRE", ""),
                        resultPaging = resultPaging
                    )
                }
                else -> {
                    repository.getMovies(viewModelScope, resultPaging = resultPaging)
                }
            }
        }

    val genres: LiveData<List<Genre>> = liveData(viewModelScope.coroutineContext) {
        when (val resp = repository.getGenres()) {
            is Result.Success -> {
                if (!resp.data.genres.isNullOrEmpty()) {
                    emit(resp.data.genres!!)
                }
            }
            is Result.Error -> println("Error: : $resp.error")
        }
    }

    fun searchMovies(keywords: String?, filterType: FilterType = FilterType.DEFAULT) {
        var query = keywords
        if (!query.isNullOrBlank()) {
            query += if (filterType == FilterType.BY_KEYWORDS) {
                "FILTER_BY_KEYWORD"
            } else {
                "FILTER_BY_GENRE"
            }
        }
        filterData.postValue(query ?: "")
    }
}
