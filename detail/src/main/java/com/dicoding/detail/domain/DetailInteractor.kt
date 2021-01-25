package com.dicoding.detail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.*
import com.dicoding.detail.data.repository.DetailRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailInteractor @Inject constructor(
    private val repository: DetailRepositoryImpl
) : DetailUseCase {
    override fun getDetail(id: Int, type: DetailType): Flow<Result<Detail>> = repository.getDetail(id, type)

    override fun getDetailCasts(id: Int, type: DetailType): Flow<Result<List<Cast>>> = repository.getDetailCasts(id, type)

    override fun getDetailVideos(id: Int, type: DetailType): Flow<Result<List<Video>>> = repository.getDetailVideos(id, type)

    override suspend fun getDetailFavored(id: Int, type: DetailType): Boolean = repository.getDetailFavored(id, type)

    override suspend fun favorDetail(detail: Detail, type: DetailType) = repository.favorDetail(detail, type)

    override fun getReviews(
        scope: CoroutineScope,
        id: Int,
        type: DetailType,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Review>> = repository.getReviews(scope, id, type, resultPaging)
}