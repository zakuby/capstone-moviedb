package com.dicoding.movie.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Genre
import com.dicoding.core.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getMovies(
        scope: CoroutineScope,
        genres: String? = "",
        keywords: String? = "",
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Movie>>

    fun getGenres(): Flow<Result<List<Genre>>>
}