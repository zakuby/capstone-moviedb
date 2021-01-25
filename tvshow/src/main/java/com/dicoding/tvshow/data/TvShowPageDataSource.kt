package com.dicoding.tvshow.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dicoding.core.data.remote.response.Result.Error
import com.dicoding.core.data.remote.response.Result.Success
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.TvShow
import com.dicoding.tvshow.data.remote.TvShowRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TvShowPageDataSource constructor(
    private val dataSource: TvShowRemoteDataSource,
    private val scope: CoroutineScope,
    private val keywords: String? = "",
    private val resultPaging: MutableLiveData<ResultPaging>
) : PageKeyedDataSource<Int, TvShow>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        resultPaging.postValue(ResultPaging.Loading(true))
        fetchTvShows {
            resultPaging.postValue(ResultPaging.Empty(it.isNullOrEmpty()))
            resultPaging.postValue(ResultPaging.Loading(false))
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        fetchTvShows(params.key) { callback.onResult(it, params.key + 1) }
    }

    private fun fetchTvShows(page: Int = 1, callback: (List<TvShow>) -> Unit) {
        scope.launch {
            when (val resp = dataSource.getTvShows(page, keywords)) {
                is Success ->  {
                    if (resp.data.results.isNullOrEmpty()){
                        resultPaging.postValue(ResultPaging.Empty(true))
                        resultPaging.postValue(ResultPaging.Loading(false))
                    } else {
                        val movies = resp.data.results ?: return@launch
                        callback(movies.map {
                            TvShow(
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
                    resultPaging.postValue(ResultPaging.Error(resp.error))
                    resultPaging.postValue(ResultPaging.Empty(true))
                    resultPaging.postValue(ResultPaging.Loading(false))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {}
}
