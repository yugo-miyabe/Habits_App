package jp.yuyuyu.habits.repository

import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitDataEntity

internal class HabitDatabaseRepositoryImpl(private val appDatabase: AppDatabase) :
    HabitDatabaseRepository {
    override suspend fun insertHabit(habitName: String) {
        appDatabase.getDao().insert(HabitDataEntity(title = habitName))
    }

    override suspend fun deleteHabits(habitName: String) {
        appDatabase.getDao().delete(HabitDataEntity(title = habitName))
    }
}