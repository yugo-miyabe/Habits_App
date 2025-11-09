package jp.yuyuyu.habits.ui.model

data class HabitCalendar(
    val habit: String,
    val calendarWeek: List<CalendarWeek>
)