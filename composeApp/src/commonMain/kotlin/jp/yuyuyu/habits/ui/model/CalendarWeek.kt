package jp.yuyuyu.habits.ui.model

import kotlinx.datetime.LocalDateTime

data class CalendarWeek(
    val monday: Calendar,
    val tuesday: Calendar,
    val wednesday: Calendar,
    val thursday: Calendar,
    val friday: Calendar,
    val saturday: Calendar,
    val sunday: Calendar
) {
    data class Calendar(
        val date: LocalDateTime,
        val isSelected: Boolean,
    )
}
