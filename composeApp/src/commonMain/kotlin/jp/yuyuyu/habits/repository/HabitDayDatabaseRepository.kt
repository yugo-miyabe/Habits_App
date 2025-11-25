package jp.yuyuyu.habits.repository

import kotlinx.datetime.LocalDate

interface HabitDayDatabaseRepository {
    suspend fun insertHabitDay(habitId: Long, date: LocalDate, isCompleted: Boolean)

    suspend fun deleteHabitDay(habitId: Long, date: LocalDate)
}
