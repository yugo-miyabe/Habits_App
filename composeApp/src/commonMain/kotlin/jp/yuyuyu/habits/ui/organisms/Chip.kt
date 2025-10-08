package jp.yuyuyu.habits.ui.organisms

import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SuggestionChip() {
    SuggestionChip(
        onClick = {},
        label = { Text("Suggestion Chip") },
    )
}

@Composable
@Preview(showBackground = true)
private fun Chip_Preview() {
    SuggestionChip()
}
