package com.dicoding.movie.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dicoding.core.data.local.models.Movie
import com.dicoding.core.data.remote.response.Result.Error
import com.dicoding.core.data.remote.response.Result.Success
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.movie.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MoviePageDataSource constructor(
    private val dataSource: MovieRemoteDataSource,
    private val scope: CoroutineScope,
    private val genres: String? = "",
    private val keywords: String? = "",
    private val resultPaging: MutableLiveData<ResultPaging>
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        resultPaging.postValue(ResultPaging.Loading(true))
        fetchMovies {
            resultPaging.postValue(ResultPaging.Empty(it.isNullOrEmpty()))
            resultPaging.postValue(ResultPaging.Loading(false))
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        fetchMovies(params.key) { callback.onResult(it, params.key + 1) }
    }

    private fun fetchMovies(page: Int = 1, callback: (List<Movie>) -> Unit) {
        scope.launch {
            when (val result = dataSource.getMovies(page, genres, keywords)) {
                is Success -> result.data.results?.let { callback(it) }
                is Error -> {
                    resultPaging.postValue(ResultPaging.Error(result.error))
                    resultPaging.postValue(ResultPaging.Empty(true))
                    resultPaging.postValue(ResultPaging.Loading(false))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}
