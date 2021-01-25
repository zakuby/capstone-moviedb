package com.dicoding.tvshow.di

import com.dicoding.tvshow.data.remote.TvShowServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class TvShowNetworkModule {

    @Provides
    @Singleton
    fun provideTvShowServices(retrofit: Retrofit): TvShowServices = retrofit.create(TvShowServices::class.java)
}
