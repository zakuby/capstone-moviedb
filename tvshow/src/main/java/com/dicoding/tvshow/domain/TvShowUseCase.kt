package com.dicoding.tvshow.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.TvShow
import kotlinx.coroutines.CoroutineScope

interface TvShowUseCase {
    fun getTvShows(
        scope: CoroutineScope,
        keywords: String? = "",
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<TvShow>>
}
