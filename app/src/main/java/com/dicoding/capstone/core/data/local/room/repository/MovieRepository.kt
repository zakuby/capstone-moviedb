package com.dicoding.capstone.core.data.local.room.repository

import com.dicoding.capstone.core.data.local.room.FavoriteDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val database: FavoriteDatabase
) {
//
//    fun removeMovie(id: Int) =
//        database.movieDao()
//            .deleteById(id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    fun saveMovie(movie: Movie) =
//        database.movieDao()
//            .insert(movie)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    fun getMovies(): Single<List<Movie>> =
//        database.movieDao()
//            .selectAll()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//
//    fun getMovieById(id: Int): Single<Movie> =
//        database.movieDao()
//            .selectById(id)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
}