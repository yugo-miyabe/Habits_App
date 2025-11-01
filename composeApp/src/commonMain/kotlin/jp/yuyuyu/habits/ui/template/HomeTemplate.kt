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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.settings_24dp
import jp.yuyuyu.habits.AdMobBanner
import jp.yuyuyu.habits.ui.organisms.Calendar
import jp.yuyuyu.habits.ui.organisms.TopBar
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTemplate(
    calendarPagerState: PagerState,
    currentDate: LocalDate,
    nextMonth: () -> Unit,
    prevMoth: () -> Unit,
    onSettingClick: () -> Unit,
) {
    var counter = remember { calendarPagerState.currentPage }

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
                HorizontalPager(
                    state = calendarPagerState,
                    pageSpacing = 8.dp,
                    snapPosition = SnapPosition.Center,
                ) { page ->
                    when {
                        page > counter -> {
                            nextMonth()
                        }

                        page < counter -> {
                            prevMoth()
                        }
                    }
                    counter = page
                    Calendar(
                        month = currentDate.month.number.toString(),
                        calendarWeekList = CalendarUtil.createMonthUIModels(currentDate),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
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
        currentDate = CalendarUtil.todayLocalDate,
        onSettingClick = { /* preview */ }
    )
}