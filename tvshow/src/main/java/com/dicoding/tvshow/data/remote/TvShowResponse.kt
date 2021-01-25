package com.dicoding.tvshow.data.remote

import com.dicoding.core.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
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
    val genres: List<Genre>? = emptyList()
)