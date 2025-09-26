package jp.yuyuyu.habits.screen.home

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.HomeTemplate
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    HomeTemplate(onSettingClick = onSettingClick)
}
