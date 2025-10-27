package jp.yuyuyu.habits.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class CalendarListConverter {
    @TypeConverter
    fun fromCalendar(calendar: HabitDay?): String? =
        calendar?.let {
            Json.encodeToString(
                HabitDay.serializer(),
                it
            )
        }

    @TypeConverter
    fun toCalendar(value: String?): HabitDay? =
        value?.let {
            Json.decodeFromString(
                HabitDay.serializer(),
                it
            )
        }

    @TypeConverter
    fun fromCalendarList(calendar: List<HabitDay>?): String? =
        calendar?.let {
            Json.encodeToString(
                kotlinx.serialization.builtins.ListSerializer(HabitDay.serializer()),
                it
            )
        }

    @TypeConverter
    fun toCalendarList(value: String?): List<HabitDay>? =
        value?.let {
            Json.decodeFromString(
                kotlinx.serialization.builtins.ListSerializer(HabitDay.serializer()),
                it
            )
        }
}