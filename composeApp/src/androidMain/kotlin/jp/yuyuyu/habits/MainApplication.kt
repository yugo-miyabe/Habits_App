package jp.yuyuyu.habits

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import jp.yuyuyu.habits.di.androidModule
import jp.yuyuyu.habits.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initAds()
        initFirebaseApp()
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

    private fun initFirebaseApp() {
        FirebaseApp.initializeApp(this)
    }
}
