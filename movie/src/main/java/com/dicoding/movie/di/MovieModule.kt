package com.dicoding.movie.di

import com.dicoding.movie.data.remote.MovieServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class MovieModule {

    @Provides
    @Singleton
    fun provideMovieServices(retrofit: Retrofit): MovieServices = retrofit.create(
        MovieServices::class.java)
}
