package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_habits
import habits.composeapp.generated.resources.settings_24dp
import jp.yuyuyu.habits.AdMobBanner
import jp.yuyuyu.habits.theme.AppTheme
import jp.yuyuyu.habits.ui.atoms.PrimaryButton
import jp.yuyuyu.habits.ui.organisms.Calendar
import jp.yuyuyu.habits.ui.organisms.TopBar
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTemplate(
    calendarPagerState: PagerState,
    currentDate: LocalDate,
    nextMonth: () -> Unit,
    prevMoth: () -> Unit,
    onAddHabitClick: () -> Unit,
    onSettingClick: () -> Unit,
) {

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
    
    Scaffold(topBar = {
        TopBar(actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    painter = painterResource(Res.drawable.settings_24dp),
                    contentDescription = null,
                )
            }
        })
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Column {
                Text(
                    text = "💪筋トレ",
                    style = AppTheme.typography.titleMediumBold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalPager(
                    state = calendarPagerState,
                    pageSpacing = 8.dp,
                    snapPosition = SnapPosition.Center,
                ) { page ->
                    Calendar(
                        month = currentDate.month.number.toString(),
                        calendarWeekList = CalendarUtil.createMonthUIModels(currentDate),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                PrimaryButton(
                    text = stringResource(Res.string.add_habits),
                    onClick = onAddHabitClick,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Box(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
                AdMobBanner()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeTemplatePreview() {
    HomeTemplate(
        calendarPagerState = rememberPagerState(
            pageCount = { 3 }
        ),
        nextMonth = { /* preview */ },
        prevMoth = { /* preview */ },
        onAddHabitClick = { /* preview */ },
        currentDate = CalendarUtil.todayLocalDate,
        onSettingClick = { /* preview */ }
    )
}