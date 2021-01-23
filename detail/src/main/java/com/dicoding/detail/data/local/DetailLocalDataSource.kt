package com.dicoding.detail.data.local

import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import javax.inject.Inject

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

    private suspend fun insertDetail(detail: Detail, type: DetailType){
        if (type == DetailType.MOVIE) {
            movieDao.insert(
                Movie(
                    id = detail.id, title = detail.title, date = detail.date,
                    description = detail.description, rate = detail.rate,
                    posterImage = detail.posterImage, backgroundImage = detail.backgroundImage, isFavorite = true
                )
            )
        } else {
            tvShowDao.insert(
                TvShow(
                    id = detail.id, title = detail.title, date = detail.date,
                    description = detail.description, rate = detail.rate,
                    posterImage = detail.posterImage, backgroundImage = detail.backgroundImage, isFavorite = true
                )
            )
        }
    }

    private suspend fun removeDetail(id: Int, type: DetailType){
        if (type == DetailType.MOVIE)
            movieDao.deleteById(id)
        else
            tvShowDao.deleteById(id)
    }
}