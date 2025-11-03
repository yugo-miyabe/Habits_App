package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_habits
import jp.yuyuyu.habits.ui.model.HabitExample
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddHabitTemplate(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(Res.string.add_habits),
                onBackClick = onBackClick,
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text("テキスト")
            }

            items(HabitExample.entries.size) { index ->
                val habitExample = HabitExample.entries[index]
                Text(text = habitExample.label)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AddHabitTemplate_Preview() {
    AddHabitTemplate(
        onBackClick = { /* preview */ }
    )
}
