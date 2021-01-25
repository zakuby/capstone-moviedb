package com.dicoding.detail.data.remote

import com.google.gson.annotations.SerializedName

data class CastResponse(
    val character: String?,
    val name: String?,
    @SerializedName("profile_path") val profileImage: String?
)
