package jp.yuyuyu.habits.ui.template

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.screen.habitManage.HabitManageUiState.HabitManageItem
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HabitManageTemplate(
    habitList: List<HabitManageItem>,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(onBackClick = onBackClick)
        }
    ) {
        // TODO: 画面作成
    }
}

@Composable
@Preview(showBackground = true)
private fun HabitManageTemplatePreview() {
    HabitManageTemplate(
        habitList = listOf(
            HabitManageItem(
                habitId = 1,
                title = "\uD83D\uDCAA筋トレ\"",
            ),
            HabitManageItem(
                habitId = 1,
                title = "\uD83C\uDF05 早起き",
            ),
        ),
        onBackClick = { /* preview */ },
    )
}

