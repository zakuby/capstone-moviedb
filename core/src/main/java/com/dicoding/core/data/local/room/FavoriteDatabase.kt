package com.dicoding.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.core.data.local.models.MovieEntity
import com.dicoding.core.data.local.models.TvShowEntity

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class
    ], version = 1, exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}
