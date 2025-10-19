package jp.yuyuyu.habits.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HabitDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val calendar: Calendar
)

data class Calendar(
    val date: String,
    val isSelected: Boolean,
)