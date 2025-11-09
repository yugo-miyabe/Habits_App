package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.model.HabitCalendar
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number

@Composable
fun CalendarPage(
    currentDate: LocalDate,
    calendarWeek: HabitCalendar,
    nextMonth: () -> Unit,
    prevMoth: () -> Unit,
) {
    val calendarPagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )

    LaunchedEffect(calendarPagerState) {
        var prev = calendarPagerState.currentPage
        snapshotFlow { calendarPagerState.currentPage }.collect { newPage ->
            when {
                newPage > prev -> {
                    nextMonth()
                    prev = newPage
                }

                newPage < prev -> {
                    prevMoth()
                    prev = newPage
                }
            }
        }
    }

    Text(
        text = calendarWeek.habit,
        style = AppTheme.typography.titleMediumBold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    HorizontalPager(
        state = calendarPagerState,
        pageSpacing = 8.dp,
        snapPosition = SnapPosition.Center,
    ) { page ->
        CalendarMonth(
            month = currentDate.month.number.toString(),
            calendarWeekList = calendarWeek.calendarWeek,
            modifier = Modifier.fillMaxWidth()
        )
    }
}