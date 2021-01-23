package com.dicoding.detail.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.Review
import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.detail.adapter.ReviewPageDataSourceFactory
import com.dicoding.detail.data.remote.DetailRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val remoteDataSource: DetailRemoteDataSource
) {
    suspend fun getDetail(id: Int, type: DetailType) = remoteDataSource.getDetail(id, type)

    suspend fun getDetailCasts(id: Int, type: DetailType) = remoteDataSource.getDetailCasts(id, type)

    suspend fun getDetailVideos(id: Int, type: DetailType) = remoteDataSource.getDetailVideos(id, type)

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