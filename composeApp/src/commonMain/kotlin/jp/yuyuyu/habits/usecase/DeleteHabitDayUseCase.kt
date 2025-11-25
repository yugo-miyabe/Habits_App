package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.repository.HabitDayDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate

class DeleteHabitDayUseCase(
    val habitDayDatabaseRepository: HabitDayDatabaseRepository
) {
    operator fun invoke(
        habitId: Long,
        date: LocalDate
    ): Flow<Either<AppError, Unit>> = flow {
        val result = habitDayDatabaseRepository.deleteHabitDay(
            habitId = habitId,
            date = date
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
