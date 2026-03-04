package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.delete_24dp
import jp.yuyuyu.habits.screen.habitManage.HabitManageUiState.HabitManageItem
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HabitManageTemplate(
    habitList: List<HabitManageItem>,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick,
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(
                items = habitList, key = { it.habitId }) { habit ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = habit.title,
                        style = AppTheme.typography.titleLarge,
                        modifier = Modifier
                            .clickable(
                                onClick = { /* TODO */ }
                            )
                            .weight(1f),
                    )
                    IconButton(
                        onClick = { /* TODO */ },
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.delete_24dp),
                            contentDescription = null,
                        )
                    }
                }
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
