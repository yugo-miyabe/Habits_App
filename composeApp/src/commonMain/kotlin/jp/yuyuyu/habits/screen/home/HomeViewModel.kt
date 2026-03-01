package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitEntity
import jp.yuyuyu.habits.ui.model.CalendarWeek
import jp.yuyuyu.habits.ui.model.HabitCalendar
import jp.yuyuyu.habits.usecase.DeleteHabitDayUseCase
import jp.yuyuyu.habits.usecase.GetAllHabitUseCase
import jp.yuyuyu.habits.usecase.GetHabitWithDay
import jp.yuyuyu.habits.usecase.InsertHabitDayUseCase
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeViewModel(
    val getAllHabitUseCase: GetAllHabitUseCase,
    val getHabitWithDay: GetHabitWithDay,
    val insertHabitDayUseCase: InsertHabitDayUseCase,
    val deleteHabitDayUseCase: DeleteHabitDayUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    fun getAllHabits() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllHabitUseCase().collect { result: Either<AppError, List<HabitEntity>> ->
                val currentDate = CalendarUtil.todayLocalDate
                result.fold(
                    ifLeft = { appError ->
                        _uiState.value = HomeUiState.Error(appError)
                    },
                    ifRight = { habitEntityList: List<HabitEntity> ->
                        val habitCalendar = habitEntityList.map { habit ->
                            var habitDayList = emptyList<LocalDate>()
                            getHabitWithDay(habit.id).collect { result ->
                                result.fold(
                                    ifLeft = { _ -> habitDayList = emptyList() },
                                    ifRight = { it ->
                                        habitDayList = it?.days?.map { it.date } ?: emptyList()
                                    }
                                )
                            }
                            HabitCalendar(
                                habitId = habit.id,
                                habit = habit.title,
                                currentDate = currentDate,
                                habitDayList = habitDayList
                            )
                        }

                        _uiState.value = HomeUiState.Success(
                            habitCalendar = habitCalendar,
                        )
                    }
                )
            }
        }
    }

    fun dismissErrorDialog() {
        _uiState.update { uiState ->
            when (uiState) {
                is HomeUiState.Error -> HomeUiState.Loading
                else -> uiState
            }
        }

        getAllHabits()
    }


    fun updateHabitCompletion(
        habitId: Long,
        date: LocalDate,
        currentlySelected: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!currentlySelected) {
                insertHabitDayUseCase(habitId = habitId, date = date).collect { result ->
                    result.fold(
                        ifLeft = { appError ->
                            _uiState.value = HomeUiState.Error(appError)
                        },
                        ifRight = {
                            updateCalendarSelectionState(
                                habitId = habitId,
                                date = date,
                                isSelected = true
                            )
                        }
                    )
                }
            } else {
                deleteHabitDayUseCase(habitId = habitId, date = date).collect { result ->
                    result.fold(
                        ifLeft = { appError ->
                            _uiState.value = HomeUiState.Error(appError)
                        },
                        ifRight = {
                            updateCalendarSelectionState(
                                habitId = habitId,
                                date = date,
                                isSelected = false
                            )
                        }
                    )
                }
            }
        }
    }

    fun onNextMonth(habit: String) = changeMonth(habit = habit) {
        CalendarUtil.plusOneMonth(it)
    }

    fun onPrevMonth(habit: String) = changeMonth(habit = habit) {
        CalendarUtil.minusOneMonth(it)
    }

    private fun changeMonth(habit: String, transform: (LocalDate) -> LocalDate) {
        // currentDate を即時更新
        _uiState.update { uiState ->
            when (uiState) {
                is HomeUiState.Success -> {
                    val habitCalendar = uiState.habitCalendar.map { habitCalendar ->
                        if (habitCalendar.habit == habit) {
                            habitCalendar.copy(
                                currentDate = transform(habitCalendar.currentDate),
                            )
                        } else {
                            habitCalendar
                        }
                    }
                    uiState.copy(habitCalendar = habitCalendar)
                }

                else -> uiState
            }
        }

        // currentDate を元に calendarWeek を再計算（非同期）
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { uiState ->
                when (uiState) {
                    is HomeUiState.Success -> {
                        uiState.copy(
                            habitCalendar = uiState.habitCalendar.map { habitCalendar ->
                                if (habitCalendar.habit == habit) {
                                    habitCalendar.copy(
                                        /*
                                        habitDays = CalendarUtil.createMonthUIModels(
                                            habitCalendar.currentDate
                                        )
                                        */
                                    )
                                } else {
                                    habitCalendar
                                }
                            }
                        )
                    }

                    else -> uiState
                }
            }
        }
    }

    private fun updateCalendarSelectionState(
        habitId: Long,
        date: LocalDate,
        isSelected: Boolean
    ) {
        _uiState.update { state ->
            when (state) {
                is HomeUiState.Success -> {
                    val updatedHabitCalendar = state.habitCalendar.map { habitCalendar ->
                        if (habitCalendar.habitId == habitId) {
                            habitCalendar.copy(
                                /*
                                habitDays = habitCalendar.habitDays.map { week ->
                                    updateWeekDaySelection(week, date, isSelected)
                                }
                                */
                            )
                        } else {
                            habitCalendar
                        }
                    }
                    state.copy(habitCalendar = updatedHabitCalendar)
                }

                else -> state
            }
        }
    }

    private fun updateWeekDaySelection(
        week: CalendarWeek,
        date: LocalDate,
        isSelected: Boolean
    ): CalendarWeek {
        fun selectIfMatch(calendar: CalendarWeek.Calendar): CalendarWeek.Calendar =
            if (calendar.date == date) calendar.copy(isSelected = isSelected) else calendar
        return week.copy(
            monday = selectIfMatch(week.monday),
            tuesday = selectIfMatch(week.tuesday),
            wednesday = selectIfMatch(week.wednesday),
            thursday = selectIfMatch(week.thursday),
            friday = selectIfMatch(week.friday),
            saturday = selectIfMatch(week.saturday),
            sunday = selectIfMatch(week.sunday)
        )
    }
}

sealed interface HomeUiState {
    data class Success(val habitCalendar: List<HabitCalendar>) : HomeUiState
    data object Loading : HomeUiState
    data class Error(val appError: AppError) : HomeUiState
}
