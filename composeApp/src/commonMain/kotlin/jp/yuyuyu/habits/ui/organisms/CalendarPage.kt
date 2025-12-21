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
import jp.yuyuyu.habits.ui.model.CalendarWeek
import jp.yuyuyu.habits.ui.model.HabitCalendar
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.number
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CalendarPage(
    calendarWeek: HabitCalendar,
    onClickCalendar: (CalendarWeek.Calendar) -> Unit,
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
            month = calendarWeek.currentDate.month.number.toString(),
            calendarWeekList = calendarWeek.calendarWeek,
            onClickCalendar = onClickCalendar,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CalendarPagePreview() {
    val list = runBlocking {
        CalendarUtil.createMonthUIModels()
    }
    val habit = HabitCalendar(
        habitID = 0,
        habit = "💪筋トレ",
        calendarWeek = list,
        currentDate = CalendarUtil.todayLocalDate
    )
    CalendarPage(
        calendarWeek = habit,
        onClickCalendar = { /* preview */ },
        nextMonth = { /* preview */ },
        prevMoth = { /* preview */ }
    )
}