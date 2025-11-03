package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddHabitTemplate(
    onBackClick: () -> Unit
) {

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Add Habit Screen")
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
