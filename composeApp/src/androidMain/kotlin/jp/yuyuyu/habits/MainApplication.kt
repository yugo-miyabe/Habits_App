package jp.yuyuyu.habits

import android.app.Application
import com.google.android.gms.ads.MobileAds
import jp.yuyuyu.habits.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initAds()
    }

    private fun initKoin() {
        initKoin {
            androidContext(this@MainApplication)
        }
    }

    private fun initAds() {
        MobileAds.initialize(this)
    }
}
