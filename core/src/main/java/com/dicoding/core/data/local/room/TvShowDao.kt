package com.dicoding.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.core.data.local.models.TvShowEntity
import com.dicoding.core.data.local.models.TvShowEntity.Companion.TABLE_NAME

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShow: List<TvShowEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): TvShowEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    fun selectAll(): LiveData<List<TvShowEntity>>

    @Update
    suspend fun update(tvShow: TvShowEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll(): Int
}
