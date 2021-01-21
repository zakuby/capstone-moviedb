package com.dicoding.core.data.remote.response

import com.dicoding.core.data.local.models.Review

data class ReviewResponse(
    val page: Int?,
    val results: List<Review>?
)