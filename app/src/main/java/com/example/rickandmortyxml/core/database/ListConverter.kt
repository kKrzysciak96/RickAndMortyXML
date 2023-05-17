package com.example.rickandmortyxml.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun convertFromStringListToJson(data: List<String>): String {
            return Gson().toJson(data)
        }

        @TypeConverter
        @JvmStatic
        fun convertFromJsonToStringList(json: String): List<String> {
            return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
        }
    }

}