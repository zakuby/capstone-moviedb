package com.dicoding.tvshow.di

import com.dicoding.tvshow.data.repository.TvShowRepository
import com.dicoding.tvshow.data.repository.TvShowRepositoryImpl
import com.dicoding.tvshow.domain.TvShowInteractor
import com.dicoding.tvshow.domain.TvShowUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class TvShowModule {

    @Binds
    abstract fun provideRepository(tvShowRepository: TvShowRepositoryImpl): TvShowRepository

    @Binds
    abstract fun provideUseCase(tvShowInteractor: TvShowInteractor): TvShowUseCase
}
