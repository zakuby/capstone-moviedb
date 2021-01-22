package com.dicoding.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.core.data.local.room.FavoriteDatabase
import com.dicoding.core.data.local.room.MovieDao
import com.dicoding.core.data.local.room.TvShowDao
import com.dicoding.core.utils.MOVIE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FavoriteDatabase =
        Room.databaseBuilder(context, FavoriteDatabase::class.java, MOVIE_DATABASE).build()

    @Provides
    @Singleton
    fun provideMovieDao(
        database: FavoriteDatabase
    ): MovieDao = database.movieDao()

    @Provides
    @Singleton
    fun provideTvShowDao(
        database: FavoriteDatabase
    ): TvShowDao = database.tvShowDao()
}
