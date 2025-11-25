package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class HabitDatabaseRepositoryImpl(private val appDatabase: AppDatabase) :
    HabitDatabaseRepository {
    override suspend fun insertHabit(habitName: String): Either<AppError, Long> = Either.catch {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
        appDatabase.getDao().insertHabit(
            HabitEntity(
                title = habitName,
                createdAt = now,
                updatedAt = now,
                isArchived = false
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
