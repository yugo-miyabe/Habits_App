package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitWithDays
import jp.yuyuyu.habits.repository.HabitDayDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHabitWithDay(
    val habitDayDatabaseRepository: HabitDayDatabaseRepository
) {
    operator fun invoke(
        habitId: Long
    ): Flow<Either<AppError, HabitWithDays?>> = flow {
        val result = habitDayDatabaseRepository.getHabitDays(habitId = habitId).fold(
            ifLeft = {
                it.left()
            },
            ifRight = {
                it.right()
            }
        )
        emit(result)
    }
}