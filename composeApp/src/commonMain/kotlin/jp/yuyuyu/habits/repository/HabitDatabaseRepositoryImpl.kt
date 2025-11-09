package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitDataEntity

internal class HabitDatabaseRepositoryImpl(private val appDatabase: AppDatabase) :
    HabitDatabaseRepository {
    override suspend fun insertHabit(habitName: String): Either<AppError, Unit> = Either.catch {
        appDatabase.getDao().insert(HabitDataEntity(title = habitName))
    }.mapLeft {
        AppError.DataBaseError
    }

    override suspend fun deleteHabits(habitName: String): Either<AppError, Unit> = Either.catch {
        appDatabase.getDao().delete(HabitDataEntity(title = habitName))
    }.mapLeft {
        AppError.DataBaseError
    }
}
