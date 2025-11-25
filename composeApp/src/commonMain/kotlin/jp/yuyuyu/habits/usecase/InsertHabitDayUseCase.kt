package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.repository.HabitDayDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertHabitDayUseCase(
    val habitDayDatabaseRepository: HabitDayDatabaseRepository
) {
    operator fun invoke(
        habitId: Long,
        date: kotlinx.datetime.LocalDate,
        isCompleted: Boolean = true
    ): Flow<Either<AppError, Unit>> = flow {
        val result = habitDayDatabaseRepository.insertHabitDay(
            habitId = habitId,
            date = date,
            isCompleted = isCompleted
        ).fold(
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