package com.dicoding.detail.ui

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.Cast
import com.dicoding.core.data.local.models.Review
import com.dicoding.core.data.local.models.Video
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.detail.data.Detail
import com.dicoding.detail.data.DetailRepository
import com.dicoding.detail.data.DetailType
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    private var detailId: Int = -1

    private var detailType: DetailType = DetailType.MOVIE

    val resultReviews = MutableLiveData<ResultPaging>()

    fun setDetail(id: Int, type: DetailType){
        detailId = id
        detailType = type
        reviews = repository.getReviews(viewModelScope, detailId, detailType, resultReviews)
    }

    val detail: LiveData<Result<Detail>> = liveData(viewModelScope.coroutineContext){
        emit(Result.Loading(true))
        when(val result = repository.getDetail(detailId, detailType)){
            is Result.Success -> emit(Result.Success(result.data))
            is Result.Error -> emit(Result.Error(result.error))
        }
        emit(Result.Loading(false))
    }
    val casts: LiveData<Result<List<Cast>>> = liveData(viewModelScope.coroutineContext){
        emit(Result.Loading(true))
        when(val result = repository.getDetailCasts(detailId, detailType)){
            is Result.Success -> {
                result.data.cast?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Error())
            }
            is Result.Error -> emit(Result.Error(result.error))
        }
        emit(Result.Loading(false))
    }

    val videos: LiveData<Result<List<Video>>> = liveData(viewModelScope.coroutineContext){
        emit(Result.Loading(true))
        when(val result = repository.getDetailVideos(detailId, detailType)){
            is Result.Success -> {
                result.data.results?.let {
                    emit(Result.Success(it))
                } ?: emit(Result.Error())
            }
            is Result.Error -> emit(Result.Error(result.error))
        }
        emit(Result.Loading(false))
    }

    lateinit var reviews: LiveData<PagedList<Review>>
}