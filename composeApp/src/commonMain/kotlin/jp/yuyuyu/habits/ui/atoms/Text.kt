package jp.yuyuyu.habits.ui.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import jp.yuyuyu.habits.theme.HabitsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HeadLine(
    text: String,
    textAlign: TextAlign? = null,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        style = style,
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun HeadLine_Preview() = HabitsTheme {
    HeadLine(text = "習慣")
}
