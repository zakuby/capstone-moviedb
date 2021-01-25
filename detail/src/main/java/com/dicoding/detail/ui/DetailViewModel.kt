package com.dicoding.detail.ui

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.dicoding.core.domain.model.Cast
import com.dicoding.core.domain.model.Review
import com.dicoding.core.domain.model.Video
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.detail.data.local.Detail
import com.dicoding.detail.data.DetailRepository
import com.dicoding.detail.data.local.DetailType
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    private var detailId: Int = -1

    private var detailType: DetailType = DetailType.MOVIE

    val resultReviews = MutableLiveData<ResultPaging>()

    val onFavoredEvent = MutableLiveData<Boolean>()

    fun setDetail(id: Int, type: DetailType){
        detailId = id
        detailType = type
        _reviews = repository.getReviews(viewModelScope, detailId, detailType, resultReviews)
        loadFavorite()
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


    private lateinit var _reviews: LiveData<PagedList<Review>>
    val reviews get() = _reviews

    private val _isFavored = MutableLiveData<Boolean>()
    val isFavored: LiveData<Boolean> get() = _isFavored

    private fun loadFavorite() = viewModelScope.launch {
        _isFavored.postValue(repository.getDetailFavored(detailId, detailType))
    }

    fun favorDetail(detail: Detail) = viewModelScope.launch {
        try{
            val isFavored = isFavored.value ?: false
            repository.favorDetail(detail.copy(isFavorite = isFavored), detailType)
            onFavoredEvent.postValue(isFavored)
            _isFavored.postValue(!isFavored)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}