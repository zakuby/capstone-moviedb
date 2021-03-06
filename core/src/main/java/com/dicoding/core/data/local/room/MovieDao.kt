package com.dicoding.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.core.data.local.models.MovieEntity
import com.dicoding.core.data.local.models.MovieEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): MovieEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    fun selectAll(): Flow<List<MovieEntity>>

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)
}
