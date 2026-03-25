package jp.yuyuyu.habits.usecase

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteHabitUseCase(
    private val habitDatabaseRepository: HabitDatabaseRepository
) {
    operator fun invoke(habitId: Long): Flow<Either<AppError, Unit>> = flow {
        val result = habitDatabaseRepository.deleteHabit(habitId = habitId)
        emit(result)
    }
}