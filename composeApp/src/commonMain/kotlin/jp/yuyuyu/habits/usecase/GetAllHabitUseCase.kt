package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllHabitUseCase(
    val habitDatabaseRepository: HabitDatabaseRepository
) {
    operator fun invoke(): Flow<Either<AppError, List<HabitDataEntity>>> = flow {
        val result = habitDatabaseRepository.getAllHabits().fold(
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
