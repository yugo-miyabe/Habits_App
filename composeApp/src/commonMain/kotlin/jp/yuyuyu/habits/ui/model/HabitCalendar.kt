package jp.yuyuyu.habits.ui.model

import kotlinx.datetime.LocalDate

data class HabitCalendar(
    val habit: String,
    val currentDate: LocalDate,
    val calendarWeek: List<CalendarWeek>
)