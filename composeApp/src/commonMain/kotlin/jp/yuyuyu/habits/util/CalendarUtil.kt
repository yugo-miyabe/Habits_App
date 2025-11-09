package jp.yuyuyu.habits.util

import jp.yuyuyu.habits.ui.model.CalendarWeek
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
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

                // 今月の開始日・終了日を決定して範囲ループで日付を作成
                val firstOfMonth = LocalDate(year, month, 1)
                val firstOfNextMonth = firstOfMonth.plus(1, DateTimeUnit.MONTH)
                val lastOfMonth = firstOfNextMonth.plus(-1, DateTimeUnit.DAY)

                // 週ごとに分割
                val weeks = mutableListOf<List<LocalDate?>>()
                var week = MutableList<LocalDate?>(7) { null }

                var current = firstOfMonth
                while (current <= lastOfMonth) {
                    val idx = when (current.dayOfWeek) {
                        DayOfWeek.MONDAY -> 0
                        DayOfWeek.TUESDAY -> 1
                        DayOfWeek.WEDNESDAY -> 2
                        DayOfWeek.THURSDAY -> 3
                        DayOfWeek.FRIDAY -> 4
                        DayOfWeek.SATURDAY -> 5
                        DayOfWeek.SUNDAY -> 6
                    }
                    week[idx] = current
                    if (idx == 6) {
                        weeks.add(week.toList())
                        week = MutableList(7) { null }
                    }
                    current = current.plus(1, DateTimeUnit.DAY)
                }
                if (week.any { it != null }) weeks.add(week.toList())

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
