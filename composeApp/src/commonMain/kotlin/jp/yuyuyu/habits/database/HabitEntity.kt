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

/**
 * 習慣(Habit)とその日付ごとの完了記録(HabitDay)を結合したデータクラス。
 *
 * Room の @Relation アノテーションを使用して、1つの習慣に紐づく
 * すべての日付記録を一度のクエリで取得するために使用されます。
 *
 * 使用例:
 * ```kotlin
 * @Transaction
 * @Query("SELECT * FROM habits")
 * fun getAllHabitsWithDays(): Flow<List<HabitWithDays>>
 * ```
 *
 * @property habit 習慣エンティティ
 * @property days 習慣に紐づく日付ごとの完了記録リスト
 */
data class HabitWithDays(
    @Embedded val habit: HabitEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId",
        entity = HabitDayEntity::class
    )
    val days: List<HabitDayEntity>
)
