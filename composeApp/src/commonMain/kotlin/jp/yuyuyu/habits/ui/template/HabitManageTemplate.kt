package jp.yuyuyu.habits.ui.template

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HabitManageTemplate(
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
        onBackClick = { /* preview */ },
    )
}

