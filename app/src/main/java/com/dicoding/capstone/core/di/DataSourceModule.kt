package com.dicoding.capstone.core.di

import com.dicoding.capstone.core.data.remote.response.ErrorResponseHandler
import com.dicoding.capstone.core.data.remote.service.TheMovieDbServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

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