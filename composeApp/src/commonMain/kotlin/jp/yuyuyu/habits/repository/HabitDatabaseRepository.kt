package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitDataEntity

interface HabitDatabaseRepository {
    suspend fun insertHabit(habitName: String): Either<AppError, Unit>

    suspend fun getAllHabits(): Either<AppError, List<HabitDataEntity>>

    suspend fun deleteHabits(habitName: String): Either<AppError, Unit>
}