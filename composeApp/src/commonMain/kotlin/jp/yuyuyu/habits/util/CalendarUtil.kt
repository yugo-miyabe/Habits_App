package jp.yuyuyu.habits.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
object CalendarUtil {
    val todayLocalDateTime: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val todayLocalDate: LocalDate = todayLocalDateTime.date

    fun getCurrentMonth(): String = todayLocalDateTime.month.number.toString()

    fun plusOneMonth(dateTime: LocalDate): LocalDate = dateTime.plus(1, DateTimeUnit.MONTH)

    fun minusOneMonth(dateTime: LocalDate): LocalDate = dateTime.minus(1, DateTimeUnit.MONTH)
}
