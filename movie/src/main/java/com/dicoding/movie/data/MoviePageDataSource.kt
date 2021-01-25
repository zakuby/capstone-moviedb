package com.dicoding.movie.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dicoding.core.data.remote.response.Result.Error
import com.dicoding.core.data.remote.response.Result.Success
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Movie
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
            resultPaging.postValue(ResultPaging.Loading(false))
            resultPaging.value = ResultPaging.Empty(it.isNullOrEmpty())
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        fetchMovies(params.key) { callback.onResult(it, params.key + 1) }
    }

    private fun fetchMovies(page: Int = 1, callback: (List<Movie>) -> Unit) {
        scope.launch {
            when (val resp = dataSource.getMovies(page, genres, keywords)) {
                is Success -> {
                    if (resp.data.results.isNullOrEmpty()) {
                        resultPaging.postValue(ResultPaging.Empty(true))
                        resultPaging.postValue(ResultPaging.Loading(false))
                    } else {
                        val movies = resp.data.results ?: return@launch
                        callback(movies.map {
                            Movie(
                                id = it.id,
                                title = it.title,
                                date = it.date,
                                description = it.description,
                                rate = it.rate,
                                backgroundImage = it.backgroundImage,
                                posterImage = it.posterImage,
                                genres = it.genres
                            )
                        })
                    }
                }
                is Error -> {
                    resultPaging.value = ResultPaging.Error(resp.error)
                    resultPaging.value = ResultPaging.Empty(true)
                    resultPaging.value = ResultPaging.Loading(false)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}
