package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateHabitUseCase(
    val habitDatabaseRepository: HabitDatabaseRepository
) {
    operator fun invoke(
        habitDataEntity: HabitDataEntity,
        date: String,
    ): Flow<Either<AppError, Unit>> = flow {
        val updatedHabitDataEntity = habitDataEntity.calendar.map {
            if (it.date == date) {
                it.copy(isSelected = it.isSelected.not())
            } else {
                it
            }
        }
        val updateHabitDataEntity = habitDataEntity.copy(calendar = updatedHabitDataEntity)
        val result = habitDatabaseRepository.updateHabit(updateHabitDataEntity).fold(
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