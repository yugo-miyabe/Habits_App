package jp.yuyuyu.habits.database

import androidx.room.RoomDatabaseConstructor
import jp.yuyuyu.habits.getDatabaseBuilder

// iOS 向けの実装: 共通の getRoomDatabase と iOS の getDatabaseBuilder() を用いて初期化します。
actual object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    actual override fun initialize(): AppDatabase {
        // getDatabaseBuilder は iOS 向けに引数なしで定義されています。
        // getRoomDatabase は共通実装で、RoomDatabase.Builder を受け取り初期化します。
        return getRoomDatabase(getDatabaseBuilder())
    }
}
