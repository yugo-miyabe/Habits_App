package jp.yuyuyu.habits.screen.habitManage

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.HabitManageTemplate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HabitManageScreen(
    onBackClick: () -> Unit,
    viewModel: HabitManageViewModel = koinViewModel()
) {
    HabitManageTemplate(
        onBackClick = onBackClick,
    )
}