package jp.yuyuyu.habits

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val version: String = BuildConfig.VERSION_NAME
}

actual fun getPlatform(): Platform = AndroidPlatform()