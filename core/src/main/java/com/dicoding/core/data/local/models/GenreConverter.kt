package com.dicoding.core.data.local.models

import androidx.room.TypeConverter
import com.dicoding.core.domain.model.Genre
import com.google.gson.Gson

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
