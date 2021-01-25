package com.dicoding.detail.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.DetailType
import com.dicoding.core.domain.model.Review
import com.dicoding.detail.data.remote.DetailRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ReviewPageDataSource(
    private val dataSource: DetailRemoteDataSource,
    private val scope: CoroutineScope,
    private val id: Int,
    private val type: DetailType,
    private val resultPaging: MutableLiveData<ResultPaging>
) : PageKeyedDataSource<Int, Review>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Review>
    ) {
        resultPaging.postValue(ResultPaging.Loading(true))
        fetchReviews {
            resultPaging.postValue(ResultPaging.Loading(false))
            resultPaging.value = ResultPaging.Empty(it.isNullOrEmpty())
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Review>) {
        fetchReviews(params.key) { callback.onResult(it, params.key + 1) }
    }

    private fun fetchReviews(page: Int = 1, callback: (List<Review>) -> Unit) {
        scope.launch {
            when (val resp = dataSource.getDetailReviews(id, type, page)) {
                is Result.Success -> {
                    if (resp.data.results.isNullOrEmpty()) {
                        resultPaging.postValue(ResultPaging.Loading(false))
                        resultPaging.value = ResultPaging.Empty(true)
                    } else {
                        val movies = resp.data.results ?: return@launch
                        callback(movies.map {
                            Review(
                                id = it.id,
                                name = "A review by ${it.author}",
                                content = it.content,
                                url = it.url
                            )
                        })
                    }
                }
                is Result.Error -> {
                    resultPaging.value = ResultPaging.Error(resp.error)
                    resultPaging.value = ResultPaging.Loading(false)
                    resultPaging.value = ResultPaging.Empty(true)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Review>) {}
}
