package com.dicoding.capstone.core.data.local.room.repository

import com.dicoding.capstone.core.data.local.room.FavoriteDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(
    private val database: FavoriteDatabase
) {
//
//    fun removeTvShow(id: Int) =
//        database.tvShowDao()
//            .deleteByTvShow(id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    fun saveTvShow(tvShow: TvShow) =
//        database.tvShowDao()
//            .insert(tvShow)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    fun getTvShows(): Single<List<TvShow>> =
//        database.tvShowDao()
//            .selectAll()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    fun getTvShowById(id: Int): Single<TvShow> =
//        database.tvShowDao()
//            .selectById(id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
}