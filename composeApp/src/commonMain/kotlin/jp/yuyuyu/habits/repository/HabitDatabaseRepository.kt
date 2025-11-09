package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError

interface HabitDatabaseRepository {
    suspend fun insertHabit(habitName: String): Either<AppError, Unit>

    suspend fun deleteHabits(habitName: String): Either<AppError, Unit>
}