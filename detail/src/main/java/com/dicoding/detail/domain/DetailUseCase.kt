package com.dicoding.detail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Cast
import com.dicoding.core.domain.model.Detail
import com.dicoding.core.domain.model.DetailType
import com.dicoding.core.domain.model.Review
import com.dicoding.core.domain.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    fun getDetail(id: Int, type: DetailType): Flow<Result<Detail>>
    fun getDetailCasts(id: Int, type: DetailType): Flow<Result<List<Cast>>>
    fun getDetailVideos(id: Int, type: DetailType): Flow<Result<List<Video>>>
    suspend fun getDetailFavored(id: Int, type: DetailType): Boolean
    suspend fun favorDetail(detail: Detail, type: DetailType)
    fun getReviews(
        scope: CoroutineScope,
        id: Int,
        type: DetailType,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Review>>
}
