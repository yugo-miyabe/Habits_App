package jp.yuyuyu.habits.ui.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import habits.composeapp.generated.resources.Res
import habits.composeapp.generated.resources.add_habits
import habits.composeapp.generated.resources.settings_24dp
import jp.yuyuyu.habits.AdMobBanner
import jp.yuyuyu.habits.ui.atoms.PrimaryButton
import jp.yuyuyu.habits.ui.model.HabitCalendar
import jp.yuyuyu.habits.ui.organisms.CalendarPage
import jp.yuyuyu.habits.ui.organisms.TopBar
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTemplate(
    habitCalendarList: List<HabitCalendar>,
    nextMonth: (habit: String) -> Unit,
    prevMoth: (habit: String) -> Unit,
    onAddHabitClick: () -> Unit,
    onSettingClick: () -> Unit,
) {

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
            LazyColumn {
                items(habitCalendarList) { calendarWeek ->
                    CalendarPage(
                        currentDate = calendarWeek.currentDate,
                        calendarWeek = calendarWeek,
                        nextMonth = {
                            nextMonth(calendarWeek.habit)
                        },
                        prevMoth = {
                            prevMoth(calendarWeek.habit)
                        }
                    )
                }

                item {
                    PrimaryButton(
                        text = stringResource(Res.string.add_habits),
                        onClick = onAddHabitClick,
                        modifier = Modifier.padding(16.dp)
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
    val list = runBlocking {
        CalendarUtil.createMonthUIModels()
    }
    val habit = HabitCalendar(
        habit = "💪筋トレ",
        calendarWeek = list,
        currentDate = CalendarUtil.todayLocalDate
    )
    HomeTemplate(
        habitCalendarList = listOf(habit),
        nextMonth = { /* preview */ },
        prevMoth = { /* preview */ },
        onAddHabitClick = { /* preview */ },
        onSettingClick = { /* preview */ }
    )
}