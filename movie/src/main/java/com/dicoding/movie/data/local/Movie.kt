package com.dicoding.movie.data.local

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.movie.data.local.Movie.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
@TypeConverters(GenreConverter::class)
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String?,
    @SerializedName("release_date")
    val date: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("vote_average")
    val rate: String?,
    @SerializedName("poster_path")
    val posterImage: String?,
    @SerializedName("backdrop_path")
    val backgroundImage: String?,
    val isMovie: Boolean? = true,
    var isFavorite: Boolean = false,
    val genres: List<Genre>? = emptyList()
) {

    companion object {
        const val TABLE_NAME = "movie"
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun convertToTvShow(): TvShow =
        TvShow(
            id = id, title = title, date = date, description = description, rate = rate,
            posterImage = posterImage, backgroundImage = backgroundImage, isFavorite = isFavorite
        )
}