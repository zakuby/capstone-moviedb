package com.dicoding.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.data.local.models.TvShow.Companion.TABLE_NAME

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShow: List<TvShow>)

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun selectById(id: Int): TvShow?

    @Query("SELECT * FROM $TABLE_NAME")
    fun selectAll(): LiveData<List<TvShow>>

    @Update
    suspend fun update(tvShow: TvShow)

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll(): Int
}
