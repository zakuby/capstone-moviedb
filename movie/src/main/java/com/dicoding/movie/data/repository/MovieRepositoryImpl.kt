package com.dicoding.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Genre
import com.dicoding.core.domain.model.Movie
import com.dicoding.movie.data.MoviePageDataSourceFactory
import com.dicoding.movie.data.remote.MovieRemoteDataSource
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovies(
        scope: CoroutineScope,
        genres: String?,
        keywords: String?,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Movie>> {
        val dataSourceFactory =
            MoviePageDataSourceFactory(
                remoteDataSource,
                scope,
                genres,
                keywords,
                resultPaging
            )
        return LivePagedListBuilder(dataSourceFactory,
            MoviePageDataSourceFactory.pagedListConfig()
        ).build()
    }

    override fun getGenres(): Flow<Result<List<Genre>>> = remoteDataSource.getMovieGenres().map {
        when (it) {
            is Result.Success -> Result.Success(it.data.genres ?: emptyList())
            is Result.Error -> Result.Error(it.error)
            is Result.Loading -> Result.Loading(it.isLoading)
        }
    }
}
