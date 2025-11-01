package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        modifier = modifier.padding(all = 4.dp)
    ) {
        Text(
            text = stringResource(Res.string.month_format, month),
            style = AppTheme.typography.titleMediumBold,
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DayWeek.entries.forEach { dayWeek ->
                Text(
                    text = dayWeek.label,
                    color = dayWeek.color,
                    style = AppTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
        }
        Column(
            modifier = Modifier.padding(vertical = 12.dp)
                .clip(RoundedCornerShape(size = 4.dp))
                .background(AppTheme.colors.backGround),
        ) {
            calendarWeekList.forEach { calendarWeek ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf(
                        calendarWeek.monday,
                        calendarWeek.tuesday,
                        calendarWeek.wednesday,
                        calendarWeek.thursday,
                        calendarWeek.friday,
                        calendarWeek.saturday,
                        calendarWeek.sunday
                    ).forEach { day ->
                        CalendarCell(
                            calendar = day,
                            modifier = Modifier.weight(1f).aspectRatio(1f).padding(4.dp)
                        )
                    }
                }
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