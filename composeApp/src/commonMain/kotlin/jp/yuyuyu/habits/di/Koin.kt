package jp.yuyuyu.habits.di

import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import jp.yuyuyu.habits.repository.HabitDatabaseRepositoryImpl
import jp.yuyuyu.habits.screen.addHabit.AddHabitViewModel
import jp.yuyuyu.habits.screen.home.HomeViewModel
import jp.yuyuyu.habits.screen.setting.SettingViewModel
import jp.yuyuyu.habits.usecase.GetAllHabitUseCase
import jp.yuyuyu.habits.usecase.InsertHabitUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.includes
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(appModule)
    }
}

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SettingViewModel() }
    viewModel { AddHabitViewModel(get()) }
    singleOf(::InsertHabitUseCase)
    singleOf(::GetAllHabitUseCase)
    //single { HabitDatabaseRepositoryImpl(appDatabase = get()) }
    singleOf(::HabitDatabaseRepositoryImpl) bind HabitDatabaseRepository::class
}
