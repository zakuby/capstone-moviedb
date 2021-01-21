package com.dicoding.movie.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson

data class Genre(
    val id: Int?,
    val name: String?,
    var isChecked: Boolean = false
) {
    fun setCheck() {
        isChecked = !isChecked
    }
}

class GenreConverter {
    @TypeConverter
    fun listToJson(value: List<Genre>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Genre>? {
        val objects = Gson().fromJson(value, Array<Genre>::class.java) as Array<Genre>
        return objects.toList()
    }
}