package jp.yuyuyu.habits.repository

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitDao
import jp.yuyuyu.habits.database.HabitDayEntity
import kotlinx.datetime.LocalDate

class HabitDayDatabaseRepositoryImpl(
    private val habitDao: HabitDao
) : HabitDayDatabaseRepository {
    override suspend fun insertHabitDay(
        habitId: Long,
        date: LocalDate,
        isCompleted: Boolean
    ): Either<AppError, Unit> = Either.catch {
        habitDao.insertDay(
            day = HabitDayEntity(
                habitId = habitId,
                date = date,
                isCompleted = isCompleted
            )
        )
    }.mapLeft {
        AppError.DataBaseError
    }

    override suspend fun deleteHabitDay(
        habitId: Long,
        date: LocalDate
    ): Either<AppError, Unit> = Either.catch {
        habitDao.deleteDay(
            day = HabitDayEntity(
                habitId = habitId,
                date = date,
                isCompleted = false
            )
        )
    }.mapLeft {
        AppError.DataBaseError
    }
}
