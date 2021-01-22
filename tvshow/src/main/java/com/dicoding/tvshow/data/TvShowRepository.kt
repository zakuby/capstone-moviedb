package com.dicoding.tvshow.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.tvshow.data.remote.TvShowRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Singleton
class TvShowRepository @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource
) {
    fun getMovies(
        scope: CoroutineScope,
        keywords: String? = "",
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<TvShow>> {
        val dataSourceFactory = TvShowPageDataSourceFactory(remoteDataSource, scope, keywords, resultPaging)
        return LivePagedListBuilder(dataSourceFactory, TvShowPageDataSourceFactory.pagedListConfig()).build()
    }
}
