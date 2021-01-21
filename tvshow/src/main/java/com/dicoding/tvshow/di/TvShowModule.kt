package com.dicoding.tvshow.di

import com.dicoding.tvshow.data.remote.TvShowServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class TvShowModule {

    @Provides
    @Singleton
    fun provideTvShowServices(retrofit: Retrofit) = retrofit.create(TvShowServices::class.java)

}