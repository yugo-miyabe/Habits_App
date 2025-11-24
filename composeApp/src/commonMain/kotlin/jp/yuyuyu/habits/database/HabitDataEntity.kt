package jp.yuyuyu.habits.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class HabitDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val calendar: List<HabitDay> = emptyList()
)

@Serializable
data class HabitDay(
    val date: String,
    val isSelected: Boolean,
)
