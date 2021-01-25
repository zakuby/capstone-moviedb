package com.dicoding.core.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dicoding.core.data.local.models.TvShowEntity.Companion.TABLE_NAME
import com.dicoding.core.domain.model.Genre

@Entity(tableName = TABLE_NAME)
@TypeConverters(GenreConverter::class)
data class TvShowEntity(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val date: String?,
    val description: String?,
    val rate: String?,
    val posterImage: String?,
    val backgroundImage: String?,
    var isFavorite: Boolean = false,
    val genres: List<Genre>? = emptyList()
) {

    companion object {

        const val TABLE_NAME = "tvshow"
    }
}
