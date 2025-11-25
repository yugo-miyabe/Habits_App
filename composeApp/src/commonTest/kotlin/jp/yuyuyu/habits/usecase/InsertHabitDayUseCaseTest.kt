package jp.yuyuyu.habits.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.repository.HabitDayDatabaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InsertHabitDayUseCaseTest {

    @Test
    fun insertHabitDaySuccess() = runBlocking {
        val fakeRepository = FakeHabitDayDatabaseRepository(insertResult = Unit.right())
        val useCase = InsertHabitDayUseCase(fakeRepository)

        val result = useCase(
            habitId = 1L,
            date = LocalDate(2024, 1, 15),
            isCompleted = true
        ).first()

        assertTrue(result.isRight())
    }

    @Test
    fun insertHabitDayDatabaseError() = runBlocking {
        val fakeRepository = FakeHabitDayDatabaseRepository(insertResult = AppError.DataBaseError.left())
        val useCase = InsertHabitDayUseCase(fakeRepository)

        val result = useCase(
            habitId = 1L,
            date = LocalDate(2024, 1, 15),
            isCompleted = true
        ).first()

        assertTrue(result.isLeft())
        assertEquals(AppError.DataBaseError, result.leftOrNull())
    }

    @Test
    fun insertHabitDayDefaultIsCompletedTrue() = runBlocking {
        val fakeRepository = FakeHabitDayDatabaseRepository(insertResult = Unit.right())
        val useCase = InsertHabitDayUseCase(fakeRepository)

        val result = useCase(
            habitId = 1L,
            date = LocalDate(2024, 1, 15)
        ).first()

        assertTrue(result.isRight())
        assertTrue(fakeRepository.lastInsertIsCompleted)
    }

    private class FakeHabitDayDatabaseRepository(
        private val insertResult: Either<AppError, Unit> = Unit.right(),
        private val deleteResult: Either<AppError, Unit> = Unit.right()
    ) : HabitDayDatabaseRepository {
        var lastInsertIsCompleted: Boolean = false
            private set

        override suspend fun insertHabitDay(
            habitId: Long,
            date: LocalDate,
            isCompleted: Boolean
        ): Either<AppError, Unit> {
            lastInsertIsCompleted = isCompleted
            return insertResult
        }

        override suspend fun deleteHabitDay(habitId: Long, date: LocalDate): Either<AppError, Unit> {
            return deleteResult
        }
    }
}
