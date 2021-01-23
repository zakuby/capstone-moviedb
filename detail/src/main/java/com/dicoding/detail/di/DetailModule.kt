package com.dicoding.detail.di

import com.dicoding.detail.data.remote.DetailServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DetailModule {
    @Provides
    @Singleton
    fun provideDetailServices(retrofit: Retrofit): DetailServices = retrofit.create(
        DetailServices::class.java)
}