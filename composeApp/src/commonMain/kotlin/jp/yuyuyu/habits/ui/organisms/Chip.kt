package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_24dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SuggestionChip() {
    SuggestionChip(
        onClick = {},
        label = { Text("Suggestion Chip") },
    )
}

@Composable
fun AddChip(
    text: String
) {
    SuggestionChip(
        onClick = {},
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.add_24dp),
                    contentDescription = null,
                )
                Text(text = text, modifier = Modifier.padding(start = 4.dp))
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
private fun Chip_Preview() {
    SuggestionChip()
}

@Composable
@Preview(showBackground = true)
private fun AddChip_Preview() {
    AddChip(text = "追加")
}