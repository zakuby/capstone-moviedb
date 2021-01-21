package com.dicoding.tvshow.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dicoding.core.data.remote.response.Result.Error
import com.dicoding.core.data.remote.response.Result.Success
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.tvshow.data.local.TvShow
import com.dicoding.tvshow.data.remote.TvShowRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TvShowPageDataSource constructor(
    private val dataSource: TvShowRemoteDataSource,
    private val scope: CoroutineScope,
    private val keywords: String? = "",
    private val resultPaging: MutableLiveData<ResultPaging>
) : PageKeyedDataSource<Int, TvShow>(){

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        resultPaging.postValue(ResultPaging.Loading(true))
        fetchMovies {
            resultPaging.postValue(ResultPaging.Empty(it.isNullOrEmpty()))
            resultPaging.postValue(ResultPaging.Loading(false))
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        fetchMovies(params.key) { callback.onResult(it,  params.key + 1) }
    }

    private fun fetchMovies(page: Int = 1, callback: (List<TvShow>) -> Unit){
        scope.launch {
            when(val result = dataSource.getTvShows(page, keywords)){
                is Success -> result.data.results?.let { callback(it) }
                is Error -> {
                    resultPaging.postValue(ResultPaging.Error(result.error))
                    resultPaging.postValue(ResultPaging.Empty(true))
                    resultPaging.postValue(ResultPaging.Loading(false))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {}
}