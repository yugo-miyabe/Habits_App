package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.model.DayWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun Calendar(

) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DayWeek.entries.forEach { dayWeek ->
                Text(
                    text = dayWeek.label,
                    color = dayWeek.color,
                    style = AppTheme.typography.labelMedium
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun Calendar_Preview() {
    Calendar()
}