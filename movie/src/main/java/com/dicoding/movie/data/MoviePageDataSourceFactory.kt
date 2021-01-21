package com.dicoding.movie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dicoding.movie.data.local.Movie
import com.dicoding.movie.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.CoroutineScope

class MoviePageDataSourceFactory(
    private val dataSource: MovieRemoteDataSource,
    private val scope: CoroutineScope,
    private val genre: String? = "",
    private val keywords: String? = ""
) : DataSource.Factory<Int, Movie>() {

    private val liveData = MutableLiveData<MoviePageDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = MoviePageDataSource(
            dataSource,
            scope,
            genre,
            keywords
        )
        liveData.postValue(source)
        return source
    }

    fun getDataSource(): LiveData<MoviePageDataSource> = liveData

    companion object {
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    }
}