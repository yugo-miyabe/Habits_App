package jp.yuyuyu.habits

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import jp.yuyuyu.habits.database.AppDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
