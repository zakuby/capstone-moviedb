package com.dicoding.movie.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.domain.model.Genre
import com.dicoding.core.domain.model.Movie
import com.dicoding.movie.data.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class MovieInteractor @Inject constructor(
    private val repository: MovieRepository
) : MovieUseCase {
    override fun getMovies(
        scope: CoroutineScope,
        genres: String?,
        keywords: String?,
        resultPaging: MutableLiveData<ResultPaging>
    ): LiveData<PagedList<Movie>> = repository.getMovies(scope, genres, keywords, resultPaging)

    override fun getGenres(): Flow<Result<List<Genre>>> = repository.getGenres()
}
