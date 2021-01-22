package com.dicoding.tvshow.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.tvshow.data.remote.TvShowRemoteDataSource
import kotlinx.coroutines.CoroutineScope

class TvShowPageDataSourceFactory(
    private val dataSource: TvShowRemoteDataSource,
    private val scope: CoroutineScope,
    private val keywords: String? = "",
    private val resultPaging: MutableLiveData<ResultPaging>
) : DataSource.Factory<Int, TvShow>() {

    private val liveData = MutableLiveData<TvShowPageDataSource>()

    override fun create(): DataSource<Int, TvShow> {
        val source = TvShowPageDataSource(
            dataSource,
            scope,
            keywords,
            resultPaging
        )
        liveData.postValue(source)
        return source
    }

    companion object {
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    }
}
