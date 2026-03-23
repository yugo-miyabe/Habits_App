package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitEntity

interface HabitDatabaseRepository {
    suspend fun insertHabit(habitName: String): Either<AppError, Long>

    suspend fun deleteHabit(habitId: Long): Either<AppError, Unit>

    suspend fun getAllHabits(): Either<AppError, List<HabitEntity>>
}