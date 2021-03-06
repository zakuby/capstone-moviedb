package com.dicoding.core.domain.model

data class Detail(
    val id: Int,
    val title: String?,
    val date: String?,
    val description: String?,
    val rate: String?,
    val posterImage: String?,
    val backgroundImage: String?,
    val isFavorite: Boolean = false,
    val genres: List<Genre>? = emptyList()
)
