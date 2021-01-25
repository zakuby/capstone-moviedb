package com.dicoding.detail.data.remote

import com.dicoding.core.domain.model.Review

data class ReviewResponse(
    val page: Int?,
    val results: List<Review>?
)
