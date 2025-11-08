package jp.yuyuyu.habits.repository

interface HabitDatabaseRepository {
    suspend  fun insertHabit(habitName: String)

    suspend fun deleteAllHabits()
}