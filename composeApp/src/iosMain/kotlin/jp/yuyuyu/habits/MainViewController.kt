package jp.yuyuyu.habits

import androidx.compose.ui.window.ComposeUIViewController
import jp.yuyuyu.habits.di.initKoin
import jp.yuyuyu.habits.di.iosModule

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin {
        modules(iosModule)
    }
}) { App() }
