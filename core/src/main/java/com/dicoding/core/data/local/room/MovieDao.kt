//package com.dicoding.core.data.local.room
//
//import androidx.room.*
//import com.dicoding.movie.data.Movie
//import com.dicoding.movie.data.Movie.Companion.TABLE_NAME
//
//@Dao
//interface MovieDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(movie: com.dicoding.movie.data.Movie)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(movies: List<com.dicoding.movie.data.Movie>)
//
//    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
//    suspend fun selectById(id: Int): com.dicoding.movie.data.Movie
//
//    @Query("SELECT * FROM $TABLE_NAME")
//    suspend fun selectAll(): List<com.dicoding.movie.data.Movie>
//
//    @Update
//    suspend fun update(movie: com.dicoding.movie.data.Movie)
//
//    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
//    suspend fun deleteById(id: Int)
//
//    @Query("DELETE FROM $TABLE_NAME")
//    suspend fun deleteAll(): Int
//}