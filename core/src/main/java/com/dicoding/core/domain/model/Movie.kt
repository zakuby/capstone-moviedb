package com.dicoding.core.domain.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String? = "",
    val date: String? = "",
    val description: String? = "",
    val rate: String? = "",
    val posterImage: String? = "",
    val backgroundImage: String? = "",
    var isFavorite: Boolean = false,
    val genres: List<Genre>? = emptyList()
)