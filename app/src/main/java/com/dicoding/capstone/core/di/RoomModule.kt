package com.dicoding.capstone.core.di

import android.app.Application
import com.dicoding.capstone.core.data.local.room.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): FavoriteDatabase = FavoriteDatabase.getInstance(application)
}