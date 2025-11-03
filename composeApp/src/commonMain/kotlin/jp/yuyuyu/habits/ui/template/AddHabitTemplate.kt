package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_habits
import jp.yuyuyu.habits.ui.atoms.PrimaryButton
import jp.yuyuyu.habits.ui.model.HabitExample
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddHabitTemplate(
    onBackClick: () -> Unit,
    onAddHabitClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(Res.string.add_habits),
                onBackClick = onBackClick,
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 72.dp)) {
                item {

                }

                item {
                    Text(
                        text = stringResource(Res.string.add_habits),
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }

                items(HabitExample.entries.size) { index ->
                    val habitExample = HabitExample.entries[index]
                    Text(text = habitExample.label)
                }
            }

            PrimaryButton(
                text = stringResource(Res.string.add_habits),
                onClick = onAddHabitClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AddHabitTemplate_Preview() {
    AddHabitTemplate(
        onBackClick = { /* preview */ },
        onAddHabitClick = { /* preview */ }
    )
}
