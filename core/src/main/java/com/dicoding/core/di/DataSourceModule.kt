package com.dicoding.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {

//    @Provides
//    @Singleton
//    fun provideMovieDataSourceFactory(
//        services: TheMovieDbServices,
//        errorResponseHandler: ErrorResponseHandler
//    ) = MovieDataSourceFactory(services, errorResponseHandler)

//    @Provides
//    @Singleton
//    fun provideTvShowDataSourceFactory(
//        services: TheMovieDbServices,
//        errorResponseHandler: ErrorResponseHandler
//    ) = TvShowDataSourceFactory(services, errorResponseHandler)
}