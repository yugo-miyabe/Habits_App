package jp.yuyuyu.habits

import android.app.Application
import com.google.android.gms.ads.MobileAds
import jp.yuyuyu.habits.di.androidModule
import jp.yuyuyu.habits.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initAds()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
            modules(androidModule)
        }
    }

    private fun initAds() {
        MobileAds.initialize(this)
    }
}
