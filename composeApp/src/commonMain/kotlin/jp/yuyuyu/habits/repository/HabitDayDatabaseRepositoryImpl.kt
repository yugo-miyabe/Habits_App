package jp.yuyuyu.habits.repository

import jp.yuyuyu.habits.database.HabitDao
import jp.yuyuyu.habits.database.HabitDayEntity
import kotlinx.datetime.LocalDate

class HabitDayDatabaseRepositoryImpl(
    private val habitDao: HabitDao
) : HabitDayDatabaseRepository {
    override suspend fun insertHabitDay(habitId: Long, date: LocalDate, isCompleted: Boolean) {
        habitDao.insertDay(
            day = HabitDayEntity(
                habitId = habitId,
                date = date,
                isCompleted = isCompleted
            )
        )
    }

    override suspend fun deleteHabitDay(habitId: Long, date: LocalDate) {
        habitDao.deleteDay(
            day = HabitDayEntity(
                habitId = habitId,
                date = date,
                isCompleted = false
            )
        )
    }
}
