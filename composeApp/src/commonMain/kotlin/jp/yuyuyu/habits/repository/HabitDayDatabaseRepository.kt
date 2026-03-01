package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitWithDays
import kotlinx.datetime.LocalDate

interface HabitDayDatabaseRepository {
    suspend fun insertHabitDay(
        habitId: Long,
        date: LocalDate,
        isCompleted: Boolean
    ): Either<AppError, Unit>

    suspend fun deleteHabitDay(habitId: Long, date: LocalDate): Either<AppError, Unit>

    suspend fun getHabitDays(habitId: Long): Either<AppError.DataBaseError, HabitWithDays?>
}
