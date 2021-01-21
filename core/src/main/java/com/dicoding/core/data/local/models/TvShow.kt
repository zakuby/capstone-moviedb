package com.dicoding.core.data.local.models

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dicoding.core.data.local.models.TvShow.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
//@TypeConverters(com.dicoding.movie.data.GenreConverter::class)
data class TvShow(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @SerializedName("original_name")
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String?,
    @SerializedName("first_air_date")
    @ColumnInfo(name = COLUMN_DATE)
    val date: String?,
    @SerializedName("overview")
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String?,
    @SerializedName("vote_average")
    @ColumnInfo(name = COLUMN_VOTE)
    val rate: String?,
    @SerializedName("poster_path")
    @ColumnInfo(name = COLUMN_POSTER)
    val posterImage: String?,
    @SerializedName("backdrop_path")
    @ColumnInfo(name = COLUMN_BACKGROUND)
    val backgroundImage: String?,
    var isFavorite: Boolean = false
//    val genres: List<com.dicoding.movie.data.Genre>? = emptyList()
) {

    companion object {

        const val TABLE_NAME = "tvshow"
        private const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DATE = "date"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_VOTE = "rate"
        const val COLUMN_POSTER = "posterImage"
        const val COLUMN_BACKGROUND = "backgroundImage"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }

//    fun convertToMovie(): com.dicoding.movie.data.Movie =
//        com.dicoding.movie.data.Movie(
//            id = id,
//            title = title,
//            date = date,
//            description = description,
//            rate = rate,
//            posterImage = posterImage,
//            backgroundImage = backgroundImage,
//            isMovie = false,
//            isFavorite = isFavorite,
//            genres = genres
//        )
}