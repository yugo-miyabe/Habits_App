package jp.yuyuyu.habits.di

import jp.yuyuyu.habits.repository.HabitDatabaseRepositoryImpl
import jp.yuyuyu.habits.screen.addHabit.AddHabitViewModel
import jp.yuyuyu.habits.screen.home.HomeViewModel
import jp.yuyuyu.habits.screen.setting.SettingViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(appModule)
    }
}

val appModule = module {
    viewModel { HomeViewModel(appDatabase = get()) }
    viewModel { SettingViewModel() }
    viewModel { AddHabitViewModel() }
    single { HabitDatabaseRepositoryImpl(appDatabase = get()) }
}
