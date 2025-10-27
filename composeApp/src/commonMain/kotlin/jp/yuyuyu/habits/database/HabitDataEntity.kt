package jp.yuyuyu.habits.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@kotlinx.serialization.Serializable
data class HabitDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val calendar: List<Calendar> = emptyList()
)

@kotlinx.serialization.Serializable
data class Calendar(
    val date: String,
    val isSelected: Boolean,
)

/*

該当範囲を `androidx.room.TypeConverters` を使うように置換しました。

```kotlin
@field:androidx.room.TypeConverters(CalendarListConverter::class)
val calendar: List<Calendar> = emptyList()
)

@kotlinx.serialization.Serializable
data class Calendar(
    val date: String,
    val isSelected: Boolean,
)

object CalendarListConverter {
    @androidx.room.TypeConverter
    fun fromCalendarList(calendar: List<Calendar>?): String? =
        calendar?.let {
            kotlinx.serialization.json.Json.encodeToString(
                kotlinx.serialization.builtins.ListSerializer(Calendar.serializer()),
                it
            )
        }

    @androidx.room.TypeConverter
    fun toCalendarList(value: String?): List<Calendar>? =
        value?.let {
            kotlinx.serialization.json.Json.decodeFromString(
                kotlinx.serialization.builtins.ListSerializer(Calendar.serializer()),
                it
            )
        }
}
```
 */
