package com.dicoding.core.domain.model

data class Genre(
    val id: Int?,
    val name: String?,
    var isChecked: Boolean = false
) {
    fun setCheck() {
        isChecked = !isChecked
    }
}
