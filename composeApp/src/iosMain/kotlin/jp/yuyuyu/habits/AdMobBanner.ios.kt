package jp.yuyuyu.habits

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIView

@Composable
actual fun AdMobBanner(modifier: Modifier) {
    // TODO 広告を実装
    UIKitView(
        factory = {
            UIView()
        },
        modifier = modifier,
    )
}
