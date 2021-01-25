package com.dicoding.favorite.di

import com.dicoding.favorite.data.repository.FavoriteRepository
import com.dicoding.favorite.data.repository.FavoriteRepositoryImpl
import com.dicoding.favorite.domain.FavoriteInteractor
import com.dicoding.favorite.domain.FavoriteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun provideRepository(favoriteRepository: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    abstract fun provideUseCase(favoriteInteractor: FavoriteInteractor): FavoriteUseCase
}
