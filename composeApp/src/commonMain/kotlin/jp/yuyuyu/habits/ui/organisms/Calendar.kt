package jp.yuyuyu.habits.ui.organisms

import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.theme.HabitsTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun Calendar() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    now.time
}

@Composable
@Preview(showBackground = true)
private fun Calendar_Preview() = HabitsTheme {
    Calendar()
}