package com.dicoding.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Cast
import com.dicoding.core.domain.model.Detail
import com.dicoding.core.domain.model.DetailType
import com.dicoding.core.domain.model.Review
import com.dicoding.core.domain.model.Video
import com.dicoding.detail.domain.DetailUseCase
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.launch

class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase
) : ViewModel() {

    private var detailId: Int = -1

    private var detailType: DetailType = DetailType.MOVIE

    val resultReviews = MutableLiveData<ResultPaging>()

    val onFavoredEvent = MutableLiveData<Boolean>()

    fun setDetail(id: Int, type: DetailType) {
        detailId = id
        detailType = type
        _reviews = useCase.getReviews(viewModelScope, detailId, detailType, resultReviews)
        loadFavorite()
    }

    val detail: LiveData<Result<Detail>> by lazy { useCase.getDetail(detailId, detailType).asLiveData(viewModelScope.coroutineContext) }
    val casts: LiveData<Result<List<Cast>>> by lazy { useCase.getDetailCasts(detailId, detailType).asLiveData(viewModelScope.coroutineContext) }
    val videos: LiveData<Result<List<Video>>> by lazy { useCase.getDetailVideos(detailId, detailType).asLiveData(viewModelScope.coroutineContext) }

    private lateinit var _reviews: LiveData<PagedList<Review>>
    val reviews get() = _reviews

    private val _isFavored = MutableLiveData<Boolean>()
    val isFavored: LiveData<Boolean> get() = _isFavored

    private fun loadFavorite() = viewModelScope.launch {
        _isFavored.postValue(useCase.getDetailFavored(detailId, detailType))
    }

    fun favorDetail(detail: Detail) = viewModelScope.launch {
        try {
            val isFavored = isFavored.value ?: false
            useCase.favorDetail(detail.copy(isFavorite = isFavored), detailType)
            onFavoredEvent.postValue(isFavored)
            _isFavored.postValue(!isFavored)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
