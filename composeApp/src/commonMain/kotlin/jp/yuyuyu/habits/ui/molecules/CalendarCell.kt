package jp.yuyuyu.habits.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.yuyuyu.habits.theme.HabitsTheme
import jp.yuyuyu.habits.ui.model.CalendarWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

@Composable
fun CalendarCell(
    calendar: CalendarWeek.Calendar,
    modifier: Modifier = Modifier
) {
    val day = calendar.date.day
    Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Text(text = day.toString())
    }
}

@OptIn(ExperimentalTime::class)
@Composable
@Preview(showBackground = true)
private fun CalendarCell_Preview() = HabitsTheme {
    val timeZone = TimeZone.currentSystemDefault()
    val isToday = Clock.System.now().toLocalDateTime(timeZone)
    val tomorrow = (isToday.toInstant(timeZone) + 1.days).toLocalDateTime(timeZone)
    Row(horizontalArrangement = Arrangement.Center) {
        CalendarCell(
            calendar = CalendarWeek.Calendar(
                date = isToday,
                isSelected = false
            )
        )
        CalendarCell(
            calendar = CalendarWeek.Calendar(
                date = tomorrow,
                isSelected = true
            )
        )
    }
}