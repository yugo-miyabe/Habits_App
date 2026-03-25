package jp.yuyuyu.habits.di

import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.getDatabaseBuilder
import jp.yuyuyu.habits.database.getRoomDatabase
import org.koin.dsl.module

val iosModule = module {
    single { getRoomDatabase(getDatabaseBuilder()) }
    single { get<AppDatabase>().getDao() }
}
