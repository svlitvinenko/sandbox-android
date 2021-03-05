package ru.svlit.sandbox.feature.weather.data.local

import androidx.room.TypeConverter

/**
 * Конвертер строки в список строк.
 */
class StringListConverter {

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(separator = "\n")
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.split("\n")
    }
}
