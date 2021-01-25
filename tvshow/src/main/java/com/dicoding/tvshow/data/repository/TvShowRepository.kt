package com.dicoding.tvshow.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Genre
import com.dicoding.core.domain.model.Movie
import com.dicoding.core.domain.model.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getTvShows(
        scope: CoroutineScope,
        keywords: String? = "",
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<TvShow>>
}