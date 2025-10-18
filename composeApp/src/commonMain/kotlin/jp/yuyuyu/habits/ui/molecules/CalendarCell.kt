package jp.yuyuyu.habits.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.yuyuyu.habits.theme.AppTheme
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
    Box(
        modifier = modifier
            .background(if (calendar.isSelected) AppTheme.colors.textPinkBackground else AppTheme.colors.textBaseFont),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = calendar.date ?: "",
            style = AppTheme.typography.labelMedium,
            color = if (calendar.isSelected) AppTheme.colors.textPinkFont else AppTheme.colors.textBaseBackground,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalTime::class)
@Composable
@Preview(showBackground = true)
private fun CalendarCell_Preview() {
    val timeZone = TimeZone.currentSystemDefault()
    val isToday = Clock.System.now().toLocalDateTime(timeZone)
    val tomorrow = (isToday.toInstant(timeZone) + 1.days).toLocalDateTime(timeZone)
    Row(horizontalArrangement = Arrangement.Center) {
        CalendarCell(
            calendar = CalendarWeek.Calendar(
                date = isToday.date.day.toString(),
                isSelected = true,
            ),
            modifier = Modifier.size(50.dp)
        )
        CalendarCell(
            calendar = CalendarWeek.Calendar(
                date = tomorrow.date.day.toString(),
                isSelected = false
            ),
            modifier = Modifier.size(50.dp)
        )
    }
}