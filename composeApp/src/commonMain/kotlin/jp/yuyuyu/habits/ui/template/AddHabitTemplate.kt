package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_habits
import habits.composeapp.generated.resources.select_template_habits
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.atoms.PrimaryButton
import jp.yuyuyu.habits.ui.model.HabitExample
import jp.yuyuyu.habits.ui.organisms.ListItemCell
import jp.yuyuyu.habits.ui.organisms.TopBar
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddHabitTemplate(
    onBackClick: () -> Unit,
    addHabitText: String,
    onTextChange: (text: String) -> Unit,
    onAddHabitClick: () -> Unit,
    isAddHabitButtonEnable: Boolean,
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
            Column {
                Text(
                    text = stringResource(Res.string.add_habits),
                    style = AppTheme.typography.titleMediumBold,
                    modifier = Modifier.padding(16.dp)
                )
                TextField(
                    value = addHabitText,
                    onValueChange = {
                        onTextChange(it)
                    },
                    placeholder = {
                        Text(
                            text = "",
                            style = AppTheme.typography.bodyMedium,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Text(
                    text = stringResource(Res.string.select_template_habits),
                    style = AppTheme.typography.titleMediumBold,
                    modifier = Modifier.padding(16.dp)
                )

                LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 72.dp)) {
                    items(HabitExample.entries.size) { index ->
                        val templateText = HabitExample.entries[index].label
                        ListItemCell(
                            title = templateText,
                            onClick = {
                                onTextChange(templateText)
                            }
                        )
                    }
                }
            }

            PrimaryButton(
                text = stringResource(Res.string.add_habits),
                onClick = onAddHabitClick,
                enabled = isAddHabitButtonEnable,
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
        onTextChange = { /* preview */ },
        onAddHabitClick = { /* preview */ },
        isAddHabitButtonEnable = false
    )
}
