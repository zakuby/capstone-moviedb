package com.dicoding.capstone.core.data.local.room.dao

import android.database.Cursor
import androidx.room.*
import com.dicoding.capstone.core.data.local.models.Movie
import com.dicoding.capstone.core.data.local.models.TvShow
import com.dicoding.capstone.core.data.local.models.TvShow.Companion.TABLE_NAME

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShow: List<TvShow>)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): TvShow

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun selectAll(): List<TvShow>

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll(): Int
}