package com.dicoding.detail.data.remote

import com.dicoding.core.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class DetailResponse(
    val id: Int,
    @SerializedName(value = "title", alternate = ["name"])
    val title: String?,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val date: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("vote_average")
    val rate: String?,
    @SerializedName("poster_path")
    val posterImage: String?,
    @SerializedName("backdrop_path")
    val backgroundImage: String?,
    val isFavorite: Boolean = false,
    val genres: List<Genre>? = emptyList()
)
