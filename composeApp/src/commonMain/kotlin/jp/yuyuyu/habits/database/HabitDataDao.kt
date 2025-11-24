package jp.yuyuyu.habits.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDataDao {
    @Insert
    suspend fun insert(habitDataEntity: HabitDataEntity)

    @Query("SELECT * FROM HabitDataEntity")
    suspend fun getAllHabits(): List<HabitDataEntity>

    @Update
    suspend fun update(vararg habitDataEntity: HabitDataEntity)

    @Delete
    suspend fun delete(habitDataEntity: HabitDataEntity)

}
