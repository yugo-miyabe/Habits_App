package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.month_format
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.model.CalendarWeek
import jp.yuyuyu.habits.ui.model.DayWeek
import jp.yuyuyu.habits.ui.molecules.CalendarCell
import jp.yuyuyu.habits.util.CalendarUtil
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Calendar(
    month: String,
    modifier: Modifier = Modifier,
    calendarWeekList: List<CalendarWeek>
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = stringResource(Res.string.month_format, month),
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )

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

        calendarWeekList.forEach { calendarWeek ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CalendarCell(
                    calendar = calendarWeek.monday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendarWeek.tuesday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendarWeek.wednesday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendarWeek.thursday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendarWeek.friday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendarWeek.saturday,
                    modifier = Modifier.weight(1f)
                )
                CalendarCell(
                    calendar = calendarWeek.sunday,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Calendar_Preview() {
    Calendar(
        month = "10",
        calendarWeekList = CalendarUtil.createMonthUIModels()
    )
}