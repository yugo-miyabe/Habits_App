package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.ui.model.HabitCalendar
import jp.yuyuyu.habits.usecase.GetAllHabitUseCase
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeViewModel(
    val getAllHabitUseCase: GetAllHabitUseCase
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
                    ifRight = { habits ->
                        val habitCalendar: List<HabitCalendar> = habits.map { habit ->
                            HabitCalendar(
                                habit = habit.title,
                                currentDate = currentDate,
                                calendarWeek = CalendarUtil.createMonthUIModels(currentDate)
                            )
                        }
                        _uiState.value = HomeUiState.Success(
                            habitCalendar = habitCalendar,
                            habits = habits
                        )
                    }
                )
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
                        val newUiState = uiState.copy(
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
                        newUiState
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
        val habits: List<HabitDataEntity> = emptyList()
    ) : HomeUiState

    data object Loading : HomeUiState
    data class Error(val appError: AppError) : HomeUiState
}
