package com.dicoding.movie.di

import com.dicoding.movie.data.remote.MovieServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class MovieNetworkModule {

    @Provides
    @Singleton
    fun provideMovieServices(retrofit: Retrofit): MovieServices = retrofit.create(
        MovieServices::class.java)
}