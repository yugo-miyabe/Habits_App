package jp.yuyuyu.habits.di

import androidx.room.Room
import jp.yuyuyu.habits.database.AppDatabase
import org.koin.dsl.module

val androidModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "habits_db"
        ).build()
    }

    single { get<AppDatabase>().getDao() }
}
