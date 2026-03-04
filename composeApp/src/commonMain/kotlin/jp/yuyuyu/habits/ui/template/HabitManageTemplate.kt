package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.yuyuyu.habits.screen.habitManage.HabitManageUiState.HabitManageItem
import jp.yuyuyu.habits.theme.AppTheme
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(
                items = habitList, key = { it.habitId }) { habit ->
                Text(
                    text = habit.title,
                    style = AppTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = { /* TODO: 画面遷移実装 */ }
                        )
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HabitManageTemplatePreview() {
    HabitManageTemplate(
        habitList = listOf(
            HabitManageItem(
                habitId = 1,
                title = "\uD83D\uDCAA筋トレ",
            ),
            HabitManageItem(
                habitId = 2,
                title = "\uD83C\uDF05 早起き",
            ),
        ),
        onBackClick = { /* preview */ },
    )
}

