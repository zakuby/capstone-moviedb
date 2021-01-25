package com.dicoding.movie.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Movie
import com.dicoding.movie.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineScope

class MoviePageDataSourceFactory(
    private val dataSource: MovieRemoteDataSource,
    private val scope: CoroutineScope,
    private val genre: String? = "",
    private val keywords: String? = "",
    private val resultPaging: MutableLiveData<ResultPaging>
) : DataSource.Factory<Int, Movie>() {

    private val liveData = MutableLiveData<MoviePageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = MoviePageDataSource(
            dataSource,
            scope,
            genre,
            keywords,
            resultPaging
        )
        liveData.postValue(source)
        return source
    }

    companion object {
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
