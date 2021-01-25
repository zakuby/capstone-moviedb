package com.dicoding.movie.data.remote

import com.dicoding.core.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class MovieResponse(
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
    val genres: List<Genre>? = emptyList()
)
