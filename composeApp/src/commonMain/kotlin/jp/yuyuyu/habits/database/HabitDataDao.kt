package jp.yuyuyu.habits.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HabitDataDao {
    @Insert
    suspend fun insert(habitDataEntity: HabitDataEntity)
}
