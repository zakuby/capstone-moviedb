package com.dicoding.movie.di

import com.dicoding.movie.data.repository.MovieRepository
import com.dicoding.movie.data.repository.MovieRepositoryImpl
import com.dicoding.movie.domain.MovieInteractor
import com.dicoding.movie.domain.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideUseCase(movieInteractor: MovieInteractor): MovieUseCase
}
