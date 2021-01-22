package com.dicoding.core.di

import androidx.lifecycle.ViewModelProvider
import com.dicoding.core.data.local.room.FavoriteDatabase
import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {

    fun provideRetrofit(): Retrofit

    fun provideFavoriteDatabase(): FavoriteDatabase

    fun provideMovieDAO(): MovieDao

    fun provideTvShowDAO(): TvShowDao
}
