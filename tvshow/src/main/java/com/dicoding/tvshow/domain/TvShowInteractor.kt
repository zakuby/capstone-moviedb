package com.dicoding.tvshow.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.TvShow
import com.dicoding.tvshow.data.repository.TvShowRepositoryImpl
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope

class TvShowInteractor @Inject constructor(
    private val repository: TvShowRepositoryImpl
) : TvShowUseCase {
    override fun getTvShows(
        scope: CoroutineScope,
        keywords: String?,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<TvShow>> = repository.getTvShows(scope, keywords, resultPaging)
}
