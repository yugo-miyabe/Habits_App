package jp.yuyuyu.habits.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.datetime.LocalDate

@Dao
interface HabitDao {

    // Habit CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity): Long

    @Query("SELECT * FROM habits")
    suspend fun getAllHabits(): List<HabitEntity>

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(days: List<HabitDayEntity>)

    /** 完了した習慣の日付を追加 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: HabitDayEntity)

    /** 完了した習慣の日付を削除 */
    @Delete
    suspend fun deleteDay(day: HabitDayEntity)

    /** あるhabit の指定期間のcalendarを取得（昇順）*/
    @Query("SELECT * FROM habit_days WHERE habitId = :habitId AND date BETWEEN :from AND :to ORDER BY date ASC")
    suspend fun getDaysInRange(habitId: Long, from: LocalDate, to: LocalDate): List<HabitDayEntity>

    /** HabitWithDays 全部（Relation を使って取得）*/
    @Transaction
    @Query("SELECT * FROM habits WHERE id = :habitId")
    suspend fun getHabitWithDays(habitId: Long): HabitWithDays?

    /** 当日チェックの有無（ユーザー時間帯で変換した LocalDate を渡す）
     *  Room のクエリで直接 Boolean を返すとマッピングで失敗するため Int(0/1) を返すようにする
     */
    @Query("SELECT EXISTS(SELECT 1 FROM habit_days WHERE habitId = :habitId AND date = :date)")
    suspend fun isCompletedOn(habitId: Long, date: LocalDate): Int

    /** 月間完了数（例：渡された年月の 1日〜末日）*/
    @Query("SELECT COUNT(*) FROM habit_days WHERE habitId = :habitId AND date BETWEEN :from AND :to")
    suspend fun countCompletedBetween(habitId: Long, from: LocalDate, to: LocalDate): Int

    /** 直近完了日の取得（ストリーク計算などに使用） */
    @Query("SELECT date FROM habit_days WHERE habitId = :habitId ORDER BY date DESC LIMIT 1")
    suspend fun getLastCompletedDate(habitId: Long): LocalDate?
}