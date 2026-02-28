package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitEntity
import jp.yuyuyu.habits.ui.model.HabitCalendar
import jp.yuyuyu.habits.usecase.DeleteHabitDayUseCase
import jp.yuyuyu.habits.usecase.GetAllHabitUseCase
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
    val insertHabitDayUseCase: InsertHabitDayUseCase,
    val deleteHabitDayUseCase: DeleteHabitDayUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    fun getAllHabits() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllHabitUseCase().collect { result ->
                val currentDate = CalendarUtil.todayLocalDate
                result.fold(
                    ifLeft = { appError ->
                        _uiState.value = HomeUiState.Error(appError)
                    },
                    ifRight = { habitEntityList ->
                        val habitCalendar: List<HabitCalendar> = habitEntityList.map { habit ->
                            HabitCalendar(
                                habitID = habit.id,
                                habit = habit.title,
                                currentDate = currentDate,
                                calendarWeek = CalendarUtil.createMonthUIModels(currentDate)
                            )
                        }
                        _uiState.value = HomeUiState.Success(
                            habitCalendar = habitCalendar,
                            habitEntityList = habitEntityList
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
        habitID: Long,
        date: LocalDate,
        isCompleted: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isCompleted) {
                insertHabitDayUseCase(
                    habitId = habitID,
                    date = date,
                    isCompleted = isCompleted
                ).collect { result ->
                    result.fold(
                        ifLeft = { appError ->
                            _uiState.value = HomeUiState.Error(appError)
                        },
                        ifRight = {
                            // 成功時の処理
                            _uiState.update { state ->
                                when (state) {
                                    is HomeUiState.Success -> {
                                        val updatedHabitCalendar =
                                            state.habitCalendar.map { habitCalendar ->
                                                if (habitCalendar.habitID == habitID) {
                                                    val updatedWeeks =
                                                        habitCalendar.calendarWeek.map { week ->
                                                            week.copy(
                                                                monday = if (week.monday.date == date) week.monday.copy(
                                                                    isSelected = true
                                                                ) else week.monday,
                                                                tuesday = if (week.tuesday.date == date) week.tuesday.copy(
                                                                    isSelected = true
                                                                ) else week.tuesday,
                                                                wednesday = if (week.wednesday.date == date) week.wednesday.copy(
                                                                    isSelected = true
                                                                ) else week.wednesday,
                                                                thursday = if (week.thursday.date == date) week.thursday.copy(
                                                                    isSelected = true
                                                                ) else week.thursday,
                                                                friday = if (week.friday.date == date) week.friday.copy(
                                                                    isSelected = true
                                                                ) else week.friday,
                                                                saturday = if (week.saturday.date == date) week.saturday.copy(
                                                                    isSelected = true
                                                                ) else week.saturday,
                                                                sunday = if (week.sunday.date == date) week.sunday.copy(
                                                                    isSelected = true
                                                                ) else week.sunday
                                                            )
                                                        }

                                                    habitCalendar.copy(calendarWeek = updatedWeeks)
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
                    )
                }
            } else {
                deleteHabitDayUseCase(habitId = habitID, date = date).collect { result ->
                    result.fold(
                        ifLeft = { appError ->
                            _uiState.value = HomeUiState.Error(appError)
                        },
                        ifRight = {
                            // 成功時の処理
                            _uiState.update { state ->
                                when (state) {
                                    is HomeUiState.Success -> {
                                        state.habitCalendar.map { habitCalendar ->
                                            if (habitCalendar.habitID == habitID) {
                                                // calendarWeek の各日付をコピーした新しいモデルで置換する
                                                val updatedWeeks =
                                                    habitCalendar.calendarWeek.map { calendarWeek ->
                                                        calendarWeek.let {
                                                            it.copy(
                                                                monday = if (it.monday.date == date) it.monday.copy(
                                                                    isSelected = false
                                                                ) else it.monday,
                                                                tuesday = if (it.tuesday.date == date) it.tuesday.copy(
                                                                    isSelected = false
                                                                ) else it.tuesday,
                                                                wednesday = if (it.wednesday.date == date) it.wednesday.copy(
                                                                    isSelected = false
                                                                ) else it.wednesday,
                                                                thursday = if (it.thursday.date == date) it.thursday.copy(
                                                                    isSelected = false
                                                                ) else it.thursday,
                                                                friday = if (it.friday.date == date) it.friday.copy(
                                                                    isSelected = false
                                                                ) else it.friday,
                                                                saturday = if (it.saturday.date == date) it.saturday.copy(
                                                                    isSelected = false
                                                                ) else it.saturday,
                                                                sunday = if (it.sunday.date == date) it.sunday.copy(
                                                                    isSelected = false
                                                                ) else it.sunday
                                                            )
                                                        }
                                                    }

                                                habitCalendar.copy(calendarWeek = updatedWeeks)
                                            } else {
                                                habitCalendar
                                            }
                                        }

                                        state
                                    }

                                    else -> state
                                }
                            }
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
                                        calendarWeek = CalendarUtil.createMonthUIModels(
                                            habitCalendar.currentDate
                                        )
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
}

sealed interface HomeUiState {
    data class Success(
        val habitCalendar: List<HabitCalendar>,
        val habitEntityList: List<HabitEntity> = emptyList()
    ) : HomeUiState

    data object Loading : HomeUiState
    data class Error(val appError: AppError) : HomeUiState
}
