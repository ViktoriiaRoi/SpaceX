package com.viktoriiaroi.core.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class LaunchConverter {
    @TypeConverter
    fun fromList(list: List<String>): String = Gson().toJson(list)

    @TypeConverter
    fun toList(string: String): List<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(string, listType)
    }
}