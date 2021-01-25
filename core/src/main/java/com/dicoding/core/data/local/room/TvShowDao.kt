package com.dicoding.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.core.data.local.models.TvShowEntity
import com.dicoding.core.data.local.models.TvShowEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShowEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): TvShowEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    fun selectAll(): Flow<List<TvShowEntity>>

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)
}
