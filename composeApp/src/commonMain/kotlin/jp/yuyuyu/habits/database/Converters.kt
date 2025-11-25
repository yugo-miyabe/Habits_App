package jp.yuyuyu.habits.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlin.jvm.JvmStatic

// シンプルに ISO-8601 の "YYYY-MM-DD" 文字列で保存する実装
object LocalDateTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromLocalDate(value: LocalDate?): String? = value?.toString() // "2025-11-24"

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
}
