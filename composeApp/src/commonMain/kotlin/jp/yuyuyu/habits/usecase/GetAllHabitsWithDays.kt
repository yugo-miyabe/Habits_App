package jp.yuyuyu.habits.usecase

import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitWithDays
import jp.yuyuyu.habits.repository.HabitDayDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllHabitsWithDays(
    val habitDayDatabaseRepository: HabitDayDatabaseRepository
) {
    operator fun invoke(): Flow<Either<AppError, List<HabitWithDays>>> = flow {
        emit(habitDayDatabaseRepository.getAllHabitsWithDays())
    }
}
