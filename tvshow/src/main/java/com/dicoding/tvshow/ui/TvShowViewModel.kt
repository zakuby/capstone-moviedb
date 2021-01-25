package com.dicoding.tvshow.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.FilterType
import com.dicoding.core.domain.model.TvShow
import com.dicoding.tvshow.domain.TvShowUseCase

class TvShowViewModel @ViewModelInject constructor(
    private val useCase: TvShowUseCase
) : ViewModel() {

    private val filterData = MutableLiveData<String>().apply { postValue("") }

    val resultPaging = MutableLiveData<ResultPaging>()

    val tvShows: LiveData<PagedList<TvShow>>
        get() = Transformations.switchMap(filterData) { filter ->
            if (filter.contains("FILTER_BY_KEYWORD"))
                useCase.getTvShows(viewModelScope, filter.replace("FILTER_BY_KEYWORD", ""), resultPaging)
            else
                useCase.getTvShows(viewModelScope, resultPaging = resultPaging)
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
