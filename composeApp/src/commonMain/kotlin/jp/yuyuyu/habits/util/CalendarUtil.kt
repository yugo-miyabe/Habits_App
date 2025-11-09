package jp.yuyuyu.habits.util

import jp.yuyuyu.habits.ui.model.CalendarWeek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
object CalendarUtil {

    val todayLocalDateTime: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val todayLocalDate: LocalDate = todayLocalDateTime.date


    fun getCurrentMonth(): String = todayLocalDateTime.month.number.toString()


    fun plusOneMonth(dateTime: LocalDate): LocalDate = dateTime.plus(1, DateTimeUnit.MONTH)

    fun minusOneMonth(dateTime: LocalDate): LocalDate = dateTime.plus(-1, DateTimeUnit.MONTH)


    /**
     * 指定月のカレンダーを作成する
     */
    suspend fun createMonthUIModels(dateTime: LocalDate = todayLocalDate): List<CalendarWeek> =
        coroutineScope {
            withContext(Dispatchers.Default) {
                val year = dateTime.year
                val month = dateTime.month

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

                delay(1000L)
                return@withContext weeks.map { week ->
                    CalendarWeek(
                        monday = CalendarWeek.Calendar(
                            date = week[0]?.day?.toString(),
                            isSelected = false
                        ),
                        tuesday = CalendarWeek.Calendar(
                            date = week[1]?.day?.toString(),
                            isSelected = false
                        ),
                        wednesday = CalendarWeek.Calendar(
                            date = week[2]?.day?.toString(),
                            isSelected = false
                        ),
                        thursday = CalendarWeek.Calendar(
                            date = week[3]?.day?.toString(),
                            isSelected = false
                        ),
                        friday = CalendarWeek.Calendar(
                            date = week[4]?.day?.toString(),
                            isSelected = false
                        ),
                        saturday = CalendarWeek.Calendar(
                            date = week[5]?.day?.toString(),
                            isSelected = false
                        ),
                        sunday = CalendarWeek.Calendar(
                            date = week[6]?.day?.toString(),
                            isSelected = false
                        ),
                    )
                }
            }
        }
}
