package com.dicoding.tvshow.data.local

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dicoding.core.data.local.models.Genre
import com.dicoding.core.data.local.models.GenreConverter
import com.dicoding.tvshow.data.local.TvShow.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
@TypeConverters(GenreConverter::class)
data class TvShow(
    @PrimaryKey
    val id: Int,
    @SerializedName("name")
    val title: String?,
    @SerializedName("first_air_date")
    val date: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("vote_average")
    val rate: String?,
    @SerializedName("poster_path")
    val posterImage: String?,
    @SerializedName("backdrop_path")
    val backgroundImage: String?,
    var isFavorite: Boolean = false,
    val genres: List<Genre>? = emptyList()
) {

    companion object {

        const val TABLE_NAME = "tvshow"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}