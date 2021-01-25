package com.dicoding.detail.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dicoding.core.domain.model.Review
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.detail.data.local.DetailType
import com.dicoding.detail.data.remote.DetailRemoteDataSource
import kotlinx.coroutines.CoroutineScope

class ReviewPageDataSourceFactory(
    private val dataSource: DetailRemoteDataSource,
    private val scope: CoroutineScope,
    private val id: Int,
    private val type: DetailType,
    private val resultPaging: MutableLiveData<ResultPaging>
) : DataSource.Factory<Int, Review>() {

    private val liveData = MutableLiveData<ReviewPageDataSource>()

    override fun create(): DataSource<Int, Review> {
        val source = ReviewPageDataSource(
            dataSource,
            scope,
            id,
            type,
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