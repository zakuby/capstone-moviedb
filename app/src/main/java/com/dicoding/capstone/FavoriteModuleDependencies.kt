package com.dicoding.capstone

import com.dicoding.core.data.local.room.FavoriteDatabase
import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {

    fun provideFavoriteDatabase(): FavoriteDatabase

    fun provideMovieDAO(): MovieDao

    fun provideTvShowDAO(): TvShowDao
}
