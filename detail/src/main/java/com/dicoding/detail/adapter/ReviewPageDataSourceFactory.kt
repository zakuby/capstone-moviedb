package com.dicoding.detail.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.DetailType
import com.dicoding.core.domain.model.Review
import com.dicoding.detail.data.remote.DetailRemoteDataSource
import kotlinx.coroutines.CoroutineScope

class ReviewPageDataSourceFactory(
    private val dataSource: DetailRemoteDataSource,
    private val scope: CoroutineScope,
    private val id: Int,
    private val type: DetailType,
    private val resultPaging: MutableLiveData<ResultPaging>
) : DataSource.Factory<Int, Review>() {

    private val liveData = MutableLiveData<ReviewPageDataSource>()

    override fun create(): DataSource<Int, Review> {
        val source = ReviewPageDataSource(
            dataSource,
            scope,
            id,
            type,
            resultPaging
        )
        liveData.postValue(source)
        return source
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Review>() {
            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    }
}
