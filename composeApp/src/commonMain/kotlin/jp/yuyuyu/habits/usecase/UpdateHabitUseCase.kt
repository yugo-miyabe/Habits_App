package jp.yuyuyu.habits.usecase

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitEntity
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateHabitUseCase(
    val habitDatabaseRepository: HabitDatabaseRepository
) {
    operator fun invoke(
        habitEntity: HabitEntity,
        date: String,
    ): Flow<Either<AppError, Unit>> = flow {
        /*
        val updatedHabitDataEntity = habitEntity.calendar.map {
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

         */
    }
}