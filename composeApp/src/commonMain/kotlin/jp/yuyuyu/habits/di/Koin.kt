package jp.yuyuyu.habits.di

import org.koin.core.context.startKoin
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

}
