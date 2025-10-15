package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.model.CalendarWeek
import jp.yuyuyu.habits.ui.model.DayWeek
import jp.yuyuyu.habits.ui.molecules.CalendarCell
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarWeekList: List<CalendarWeek> = emptyList()
) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val calendarList = CalendarUtil.createMonthUIModels()
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DayWeek.entries.forEach { dayWeek ->
                Text(
                    text = dayWeek.label,
                    color = dayWeek.color,
                    style = AppTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
        }

        calendarList.forEach { calendar ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalendarCell(
                    calendar = calendar.monday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendar.tuesday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendar.wednesday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendar.thursday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendar.friday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendar.saturday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendar.sunday,
                    modifier = Modifier.weight(1f)
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