package jp.yuyuyu.habits.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class CalendarListConverter {
    @TypeConverter
    fun fromCalendar(calendar: Calendar?): String? =
        calendar?.let {
            Json.encodeToString(
                Calendar.serializer(),
                it
            )
        }

    @TypeConverter
    fun toCalendar(value: String?): Calendar? =
        value?.let {
            Json.decodeFromString(
                Calendar.serializer(),
                it
            )
        }

    @TypeConverter
    fun fromCalendarList(calendar: List<Calendar>?): String? =
        calendar?.let {
            Json.encodeToString(
                kotlinx.serialization.builtins.ListSerializer(Calendar.serializer()),
                it
            )
        }

    @TypeConverter
    fun toCalendarList(value: String?): List<Calendar>? =
        value?.let {
            Json.decodeFromString(
                kotlinx.serialization.builtins.ListSerializer(Calendar.serializer()),
                it
            )
        }
}