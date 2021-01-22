package com.dicoding.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.movie.data.remote.MovieRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) {

    fun getMovies(
        scope: CoroutineScope,
        genres: String? = "",
        keywords: String? = "",
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Movie>> {
        val dataSourceFactory = MoviePageDataSourceFactory(remoteDataSource, scope, genres, keywords, resultPaging)
        return LivePagedListBuilder(dataSourceFactory, MoviePageDataSourceFactory.pagedListConfig()).build()
    }

    suspend fun getGenres() = remoteDataSource.getMovieGenres()
}
