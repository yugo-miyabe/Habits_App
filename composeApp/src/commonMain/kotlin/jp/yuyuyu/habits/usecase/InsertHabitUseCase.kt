package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertHabitUseCase(
    val habitDatabaseRepository: HabitDatabaseRepository
) {
    operator fun invoke(habitName: String): Flow<Either<AppError, Unit>> = flow {
        val result = habitDatabaseRepository.insertHabit(habitName).fold(
            ifLeft = {
                AppError.DataBaseError.left()
            },
            ifRight = {
                it.right()
            }
        )
        emit(result)
    }
}
