package com.dicoding.capstone.core.data.remote.response

import com.dicoding.capstone.core.data.local.models.Review

data class ReviewResponse(
    val page: Int?,
    val results: List<Review>?
)