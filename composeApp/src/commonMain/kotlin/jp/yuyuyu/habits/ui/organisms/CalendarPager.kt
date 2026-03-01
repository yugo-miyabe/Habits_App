package jp.yuyuyu.habits.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.theme.HabitsTheme
import jp.yuyuyu.habits.ui.model.HabitCalendar
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock

@Composable
@OptIn(kotlin.time.ExperimentalTime::class)
fun CalendarPager(
    habitDateList: List<LocalDate>,
    onDateClick: (habitDate: LocalDate, isHabitDay: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    // 現在日時を取得
    val currentDate = remember {
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    val currentYear = currentDate.year
    // 月の数値 (1..12) を取得
    val currentMonthValue = currentDate.month.number
    val startYear = currentYear - 10
    val endYear = currentYear + 10

    // inclusiveの年数を使って正しく総月数を計算
    val totalMonths = (endYear - startYear + 1) * 12

    // 現在の年月に対応するページを初期ページに設定
    val initialPage = (currentYear - startYear) * 12 + (currentMonthValue - 1)

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { totalMonths }
    )

    val currentDisplayMonth by remember {
        derivedStateOf {
            val pageIndex = pagerState.currentPage
            val displayYear = startYear + (pageIndex / 12)
            val displayMonthValue = (pageIndex % 12) + 1
            Pair(displayYear, displayMonthValue)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // ヘッダー（月の表示と前後ボタン）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage > 0) {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }
            ) {
                // アイコンライブラリが利用できない環境向けにテキスト矢印を使用
                Text(text = "◀", fontSize = 20.sp)
            }

            Text(
                text = "${currentDisplayMonth.first}年 ${currentDisplayMonth.second}月",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < totalMonths - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            ) {
                Text(text = "▶", fontSize = 20.sp)
            }
        }

        // 曜日ヘッダー
        DayOfWeekHeader()

        // カレンダーページャー
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val displayYear = startYear + (page / 12)
            val displayMonthValue = (page % 12) + 1
            MonthCalendar(
                year = displayYear,
                monthValue = displayMonthValue,
                habitDays = habitDateList,
                onDateClick = onDateClick
            )
        }
    }
}

@Composable
private fun DayOfWeekHeader() {
    val daysOfWeek = listOf("日", "月", "火", "水", "木", "金", "土")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        daysOfWeek.forEachIndexed { index, day ->
            Text(
                text = day,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = when (index) {
                    0 -> AppTheme.colors.sunday    // 日曜日
                    6 -> AppTheme.colors.saturday  // 土曜日
                    else -> AppTheme.colors.black
                }
            )
        }
    }
}

@Composable
@OptIn(kotlin.time.ExperimentalTime::class)
private fun MonthCalendar(
    year: Int,
    monthValue: Int,
    habitDays: List<LocalDate>,
    onDateClick: (date: LocalDate, isHabitDay: Boolean) -> Unit,
) {
    // habitDays を Set にしてルックアップを O(1) に最適化
    val habitDaySet: Set<LocalDate> = remember(habitDays) { habitDays.toSet() }

    // 月の最初の日を作成
    val firstDayOfMonth = LocalDate(year, monthValue, 1)
    // 月の日数を取得
    val daysInMonth = LocalDate(year, monthValue, 1)
        .plus(1, DateTimeUnit.MONTH)
        .minus(1, DateTimeUnit.DAY).day

    // 月の最初の日の曜日を取得（日曜日を0にする）
    val firstDayOfWeek = when (firstDayOfMonth.dayOfWeek) {
        DayOfWeek.MONDAY -> 1
        DayOfWeek.TUESDAY -> 2
        DayOfWeek.WEDNESDAY -> 3
        DayOfWeek.THURSDAY -> 4
        DayOfWeek.FRIDAY -> 5
        DayOfWeek.SATURDAY -> 6
        DayOfWeek.SUNDAY -> 0
    }

    // 今日の日付をLocalDateとして取得
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        // カレンダーグリッドを週ごとに表示
        var dayCounter = 1 - firstDayOfWeek

        repeat(6) { _ -> // 最大6週間
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                repeat(7) { dayOfWeekIndex ->
                    val day = dayCounter
                    dayCounter++

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (day in 1..daysInMonth) {
                            val date = LocalDate(year, monthValue, day)
                            val isToday = date == today
                            val isHabitDay = habitDaySet.contains(date)

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        when {
                                            isToday -> MaterialTheme.colorScheme.primaryContainer
                                            isHabitDay -> MaterialTheme.colorScheme.secondaryContainer
                                            else -> Color.Transparent
                                        }
                                    )
                                    .border(
                                        width = if (isToday || isHabitDay) 2.dp else 0.dp,
                                        color = when {
                                            isToday -> MaterialTheme.colorScheme.primary
                                            isHabitDay -> MaterialTheme.colorScheme.secondary
                                            else -> Color.Transparent
                                        }
                                    )
                                    .clickable { onDateClick(date, isHabitDay) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.toString(),
                                    fontSize = 16.sp,
                                    color = when (dayOfWeekIndex) {
                                        0 -> AppTheme.colors.sunday    // 日曜日
                                        6 -> AppTheme.colors.saturday  // 土曜日
                                        else -> AppTheme.colors.black
                                    },
                                    fontWeight = if (isToday || isHabitDay) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            // 全ての日を表示したら終了
            if (dayCounter > daysInMonth) return@Column
        }
    }
}

@Composable
@Preview(showBackground = true)
@OptIn(kotlin.time.ExperimentalTime::class)
private fun CalendarPagerPreview() = HabitsTheme {
    val todayLocalDate: LocalDate =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    val habitCalendar = HabitCalendar(
        habitId = 0,
        habit = "💪筋トレ",
        currentDate = CalendarUtil.todayLocalDate,
        habitDayList = listOf(
            todayLocalDate,
            todayLocalDate.plus(1, DateTimeUnit.DAY),
            todayLocalDate.plus(2, DateTimeUnit.DAY),
            todayLocalDate.plus(15, DateTimeUnit.DAY)
        ),
    )
    CalendarPager(
        habitDateList = habitCalendar.habitDayList,
        onDateClick = { _, _ -> /* preview */ }
    )
}

@Composable
@Preview(showBackground = true)
private fun DayOfWeekHeaderPreview() = HabitsTheme {
    DayOfWeekHeader()
}

@Composable
@Preview(showBackground = true)
private fun MonthCalendarPreview() = HabitsTheme {
    MonthCalendar(
        year = 2025,
        monthValue = 2,
        habitDays = emptyList(),
        onDateClick = { _, _ -> /* preview */ }
    )
}
