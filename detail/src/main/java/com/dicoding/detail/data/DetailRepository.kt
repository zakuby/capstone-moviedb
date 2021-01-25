package com.dicoding.detail.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.domain.model.Review
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.detail.adapter.ReviewPageDataSourceFactory
import com.dicoding.detail.data.local.Detail
import com.dicoding.detail.data.local.DetailType
import com.dicoding.detail.data.local.DetailLocalDataSource
import com.dicoding.detail.data.remote.DetailRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val localDataSource: DetailLocalDataSource,
    private val remoteDataSource: DetailRemoteDataSource
) {
    suspend fun getDetail(id: Int, type: DetailType) = remoteDataSource.getDetail(id, type)

    suspend fun getDetailCasts(id: Int, type: DetailType) = remoteDataSource.getDetailCasts(id, type)

    suspend fun getDetailVideos(id: Int, type: DetailType) = remoteDataSource.getDetailVideos(id, type)

    suspend fun getDetailFavored(id: Int, type: DetailType) = localDataSource.isFavored(id, type)

    suspend fun favorDetail(detail: Detail, type: DetailType) = localDataSource.favorDetail(detail, type)

    fun getReviews(
        scope: CoroutineScope,
        id: Int,
        type: DetailType,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Review>> {
        val dataSourceFactory = ReviewPageDataSourceFactory(remoteDataSource, scope, id, type, resultPaging)
        return LivePagedListBuilder(dataSourceFactory, ReviewPageDataSourceFactory.pagedListConfig()).build()
    }
}