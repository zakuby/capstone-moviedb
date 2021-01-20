package com.dicoding.capstone.core.data.local.room.dao

import androidx.room.*
import com.dicoding.capstone.core.data.local.models.Movie
import com.dicoding.capstone.core.data.local.models.Movie.Companion.TABLE_NAME

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): Movie

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun selectAll(): List<Movie>

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll(): Int
}