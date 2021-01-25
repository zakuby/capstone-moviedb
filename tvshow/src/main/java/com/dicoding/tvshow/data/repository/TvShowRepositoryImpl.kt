package com.dicoding.tvshow.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.domain.model.TvShow
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.tvshow.data.TvShowPageDataSourceFactory
import com.dicoding.tvshow.data.remote.TvShowRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {
    override fun getTvShows(
        scope: CoroutineScope,
        keywords: String?,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<TvShow>> {
        val dataSourceFactory =
            TvShowPageDataSourceFactory(
                remoteDataSource,
                scope,
                keywords,
                resultPaging
            )
        return LivePagedListBuilder(dataSourceFactory,
            TvShowPageDataSourceFactory.pagedListConfig()
        ).build()
    }
}
