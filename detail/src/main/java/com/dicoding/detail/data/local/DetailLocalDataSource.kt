package com.dicoding.detail.data.local

import com.dicoding.core.data.local.models.MovieEntity
import com.dicoding.core.data.local.models.TvShowEntity
import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import com.dicoding.core.domain.model.Detail
import com.dicoding.core.domain.model.DetailType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao
) {
    suspend fun isFavored(id: Int, type: DetailType): Boolean =
        if (type == DetailType.MOVIE)
            movieDao.selectById(id) != null
        else
            tvShowDao.selectById(id) != null

    suspend fun favorDetail(detail: Detail, type: DetailType) {
        if (detail.isFavorite) removeDetail(detail.id, type)
        else insertDetail(detail, type)
    }

    private suspend fun insertDetail(detail: Detail, type: DetailType) {
        if (type == DetailType.MOVIE) {
            movieDao.insert(
                MovieEntity(
                    id = detail.id,
                    title = detail.title,
                    date = detail.date,
                    description = detail.description,
                    rate = detail.rate,
                    posterImage = detail.posterImage,
                    backgroundImage = detail.backgroundImage,
                    isFavorite = true
                )
            )
        } else {
            tvShowDao.insert(
                TvShowEntity(
                    id = detail.id,
                    title = detail.title,
                    date = detail.date,
                    description = detail.description,
                    rate = detail.rate,
                    posterImage = detail.posterImage,
                    backgroundImage = detail.backgroundImage,
                    isFavorite = true
                )
            )
        }
    }

    private suspend fun removeDetail(id: Int, type: DetailType) {
        if (type == DetailType.MOVIE)
            movieDao.deleteById(id)
        else
            tvShowDao.deleteById(id)
    }
}
