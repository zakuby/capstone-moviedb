package com.dicoding.core.data.local.room

import androidx.room.*
import com.dicoding.core.data.local.models.MovieEntity
import com.dicoding.core.data.local.models.MovieEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): MovieEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    fun selectAll(): Flow<List<MovieEntity>>

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll(): Int
}
