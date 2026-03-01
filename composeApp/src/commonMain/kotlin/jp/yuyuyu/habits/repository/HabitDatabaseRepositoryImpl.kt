package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitEntity

internal class HabitDatabaseRepositoryImpl(private val appDatabase: AppDatabase) :
    HabitDatabaseRepository {
    override suspend fun insertHabit(habitName: String): Either<AppError, Long> = Either.catch {
        appDatabase.getDao().insertHabit(
            HabitEntity(
                title = habitName,
                isArchived = true
            )
        )
    }.mapLeft {
        AppError.DataBaseError
    }

    override suspend fun getAllHabits(): Either<AppError, List<HabitEntity>> = Either.catch {
        appDatabase.getDao().getAllHabits()
    }.mapLeft {
        AppError.DataBaseError
    }
}
