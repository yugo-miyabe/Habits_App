package jp.yuyuyu.habits.util

import jp.yuyuyu.habits.ui.model.CalenderUIModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
object CalendarUtil {
    fun createMonthUIModels(): List<CalenderUIModel> {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val year = now.year
        val month = now.month

        // 今月の日付リストを作成
        val days = mutableListOf<LocalDate>()
        var day = 1
        while (true) {
            try {
                days.add(LocalDate(year, month, day))
                day++
            } catch (_: Throwable) {
                break
            }
        }

        // 週ごとに分割
        val weeks = mutableListOf<List<LocalDate?>>()
        var week = MutableList<LocalDate?>(7) { null }
        for (date in days) {
            val idx = when (date.dayOfWeek) {
                kotlinx.datetime.DayOfWeek.MONDAY -> 0
                kotlinx.datetime.DayOfWeek.TUESDAY -> 1
                kotlinx.datetime.DayOfWeek.WEDNESDAY -> 2
                kotlinx.datetime.DayOfWeek.THURSDAY -> 3
                kotlinx.datetime.DayOfWeek.FRIDAY -> 4
                kotlinx.datetime.DayOfWeek.SATURDAY -> 5
                kotlinx.datetime.DayOfWeek.SUNDAY -> 6
            }
            week[idx] = date
            if (idx == 6) {
                weeks.add(week.toList())
                week = MutableList(7) { null }
            }
        }
        if (week.any { it != null }) weeks.add(week.toList())

        return weeks.map { week ->
            CalenderUIModel(
                mondayOfDay = week[0]?.day?.toString(),
                tuesdayOfDay = week[1]?.day?.toString(),
                wednesdayOfDay = week[2]?.day?.toString(),
                thursdayOfDay = week[3]?.day?.toString(),
                fridayOfDay = week[4]?.day?.toString(),
                saturdayOfDay = week[5]?.day?.toString(),
                sundayOfDay = week[6]?.day?.toString()
            )
        }
    }
}
