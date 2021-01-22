package com.dicoding.tvshow.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.FilterType
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.tvshow.data.TvShowRepository

class TvShowViewModel @ViewModelInject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    private val filterData = MutableLiveData<String>().apply { postValue("") }

    val resultPaging = MutableLiveData<ResultPaging>()

    val tvShows: LiveData<PagedList<TvShow>>
        get() = Transformations.switchMap(filterData) { filter ->
            if (filter.contains("FILTER_BY_KEYWORD"))
                repository.getMovies(viewModelScope, filter.replace("FILTER_BY_KEYWORD", ""), resultPaging)
            else
                repository.getMovies(viewModelScope, resultPaging = resultPaging)
        }

    fun searchTvShows(keywords: String?, filterType: FilterType = FilterType.DEFAULT) {
        var query = keywords
        if (!query.isNullOrBlank()) {
            if (filterType == FilterType.BY_KEYWORDS) {
                query += "FILTER_BY_KEYWORD"
            }
        }
        filterData.postValue(query ?: "")
    }
}
