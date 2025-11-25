package jp.yuyuyu.habits.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.datetime.LocalDate

@Entity(
    tableName = "habits"
)
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val isArchived: Boolean,
)

@Entity(
    tableName = "habit_days",
    primaryKeys = ["habitId"],
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["habitId"]),
        Index(value = ["date"])
    ]
)
data class HabitDayEntity(
    val habitId: Long,
    @ColumnInfo(name = "date")
    val date: LocalDate,
    val isCompleted: Boolean = false,
)

data class HabitWithDays(
    @Embedded val habit: HabitEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId",
        entity = HabitDayEntity::class
    )
    val days: List<HabitDayEntity>
)
