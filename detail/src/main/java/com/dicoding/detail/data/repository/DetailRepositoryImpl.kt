package com.dicoding.detail.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.ErrorResponse
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Cast
import com.dicoding.core.domain.model.Detail
import com.dicoding.core.domain.model.DetailType
import com.dicoding.core.domain.model.Review
import com.dicoding.core.domain.model.Video
import com.dicoding.detail.adapter.ReviewPageDataSourceFactory
import com.dicoding.detail.data.local.DetailLocalDataSource
import com.dicoding.detail.data.remote.DetailRemoteDataSource
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map

class DetailRepositoryImpl @Inject constructor(
    private val localDataSource: DetailLocalDataSource,
    private val remoteDataSource: DetailRemoteDataSource
) : DetailRepository {
    override fun getDetail(id: Int, type: DetailType) = remoteDataSource.getDetail(id, type).map {
        when (it) {
            is Result.Success -> {
                val detail = it.data
                Result.Success(Detail(
                    id = detail.id,
                    title = detail.title,
                    date = detail.date,
                    description = detail.description,
                    rate = detail.rate,
                    backgroundImage = detail.backgroundImage,
                    posterImage = detail.posterImage,
                    genres = detail.genres
                ))
            }
            is Result.Error -> Result.Error(it.error)
            is Result.Loading -> Result.Loading(it.isLoading)
        }
    }

    override fun getDetailCasts(id: Int, type: DetailType) = remoteDataSource.getDetailCasts(id, type).map { result ->
        when (result) {
            is Result.Success -> {
                val genres = result.data.cast
                if (genres.isNullOrEmpty())
                    Result.Error(ErrorResponse())
                else
                    Result.Success(genres.map { Cast(character = it.character, name = it.name, profileImage = it.profileImage) })
            }
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading(result.isLoading)
        }
    }

    override fun getDetailVideos(id: Int, type: DetailType) = remoteDataSource.getDetailVideos(id, type).map { result ->
        when (result) {
            is Result.Success -> {
                val videos = result.data.results
                if (videos.isNullOrEmpty())
                    Result.Error(ErrorResponse())
                else
                    Result.Success(videos.map { Video(key = it.key, name = it.name, site = it.site) })
            }
            is Result.Error -> Result.Error(result.error)
            is Result.Loading -> Result.Loading(result.isLoading)
        }
    }

    override suspend fun getDetailFavored(id: Int, type: DetailType) = localDataSource.isFavored(id, type)

    override suspend fun favorDetail(detail: Detail, type: DetailType) = localDataSource.favorDetail(detail, type)

    override fun getReviews(
        scope: CoroutineScope,
        id: Int,
        type: DetailType,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Review>> {
        val dataSourceFactory = ReviewPageDataSourceFactory(remoteDataSource, scope, id, type, resultPaging)
        return LivePagedListBuilder(dataSourceFactory, ReviewPageDataSourceFactory.pagedListConfig()).build()
    }
}
