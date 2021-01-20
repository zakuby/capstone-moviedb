package com.dicoding.capstone.core.data.remote.response

import com.dicoding.capstone.core.data.local.models.Cast

class MovieCreditsResponse(
    val id: Int?,
    val cast: List<Cast>?
)