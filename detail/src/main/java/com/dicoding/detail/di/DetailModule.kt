package com.dicoding.detail.di

import com.dicoding.detail.data.repository.DetailRepository
import com.dicoding.detail.data.repository.DetailRepositoryImpl
import com.dicoding.detail.domain.DetailInteractor
import com.dicoding.detail.domain.DetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DetailModule {

    @Binds
    abstract fun provideRepository(detailRepository: DetailRepositoryImpl): DetailRepository

    @Binds
    abstract fun provideUseCase(detailInteractor: DetailInteractor): DetailUseCase
}
