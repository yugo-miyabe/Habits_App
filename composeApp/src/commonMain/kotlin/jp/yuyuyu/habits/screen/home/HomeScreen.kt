package jp.yuyuyu.habits.screen.home

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.HomeTemplate


@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
) {
    HomeTemplate(onSettingClick = onSettingClick)
}
