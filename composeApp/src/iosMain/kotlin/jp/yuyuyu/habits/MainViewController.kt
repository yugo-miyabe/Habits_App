package jp.yuyuyu.habits

import androidx.compose.ui.window.ComposeUIViewController
import jp.yuyuyu.habits.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }