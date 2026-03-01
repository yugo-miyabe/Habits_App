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
import jp.yuyuyu.habits.ui.organisms.CalendarPager
import jp.yuyuyu.habits.ui.organisms.TopBar
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@Composable
fun HomeTemplate(
    habitCalendarList: List<HabitCalendar>,
    onDateClick: (habitId: Long, habitDay: LocalDate, isHabitDay: Boolean) -> Unit,
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
                items(habitCalendarList) { habitCalendar ->
                    CalendarPager(
                        habitDateList = habitCalendar.habitDayList,
                        onDateClick = { date, isHabitDay ->
                            onDateClick(
                                habitCalendar.habitId,
                                date,
                                isHabitDay
                            )
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
    val habit = HabitCalendar(
        habitId = 0,
        habit = "💪筋トレ",
        currentDate = CalendarUtil.todayLocalDate,
        habitDayList = emptyList()
    )
    HomeTemplate(
        habitCalendarList = listOf(habit),
        onDateClick = { _, _, _ -> /* preview */ },
        onAddHabitClick = { /* preview */ },
        onSettingClick = { /* preview */ }
    )
}
