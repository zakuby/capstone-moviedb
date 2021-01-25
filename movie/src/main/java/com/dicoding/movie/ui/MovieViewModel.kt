package com.dicoding.movie.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.FilterType
import com.dicoding.core.domain.model.Genre
import com.dicoding.core.domain.model.Movie
import com.dicoding.movie.domain.MovieUseCase

class MovieViewModel @ViewModelInject constructor(
    private val useCase: MovieUseCase
) : ViewModel() {

    private val filterData = MutableLiveData<String>().apply {
        postValue("")
    }

    val resultPaging = MutableLiveData<ResultPaging>()

    val movies: LiveData<PagedList<Movie>>
        get() = Transformations.switchMap(filterData) { filter ->
            when {
                filter.contains("FILTER_BY_KEYWORD") -> {
                    useCase.getMovies(
                        viewModelScope,
                        keywords = filter.replace("FILTER_BY_KEYWORD", ""),
                        resultPaging = resultPaging
                    )
                }
                filter.contains("FILTER_BY_GENRE") -> {
                    useCase.getMovies(
                        viewModelScope,
                        genres = filter.replace("FILTER_BY_GENRE", ""),
                        resultPaging = resultPaging
                    )
                }
                else -> {
                    useCase.getMovies(viewModelScope, resultPaging = resultPaging)
                }
            }
        }

    val genres: LiveData<Result<List<Genre>>> = useCase.getGenres().asLiveData(viewModelScope.coroutineContext)

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
