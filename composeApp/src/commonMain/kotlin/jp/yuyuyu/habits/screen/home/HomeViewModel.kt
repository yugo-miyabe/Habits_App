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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllHabitUseCase().collect { result ->
                result.fold(
                    ifLeft = { appError ->
                        _uiState.value = HomeUiState.Error(appError)
                    },
                    ifRight = { habits ->
                        val habitCalendar : List<HabitCalendar> = habits.map { habit ->
                            HabitCalendar(
                                habit = habit.title,
                                calendarWeek = CalendarUtil.createMonthUIModels(CalendarUtil.todayLocalDate)
                            )
                        }
                        _uiState.value = HomeUiState.Success(
                            currentDate = CalendarUtil.todayLocalDate,
                            habitCalendar = habitCalendar,
                            habits = habits
                        )
                    }
                )
            }
        }
    }

    fun onNextMonth() = changeMonth { CalendarUtil.plusOneMonth(it) }

    fun onPrevMonth() = changeMonth { CalendarUtil.minusOneMonth(it) }

    private fun changeMonth(transform: (LocalDate) -> LocalDate) {
        _uiState.update { uiState ->
            when (uiState) {
                is HomeUiState.Success -> uiState.copy(currentDate = transform(uiState.currentDate))

                else -> uiState
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { uiState ->
                when (uiState) {
                    is HomeUiState.Success -> {
                        uiState.copy(
                            habitCalendar = uiState.habitCalendar.map {
                                it.copy(
                                    calendarWeek = CalendarUtil.createMonthUIModels(uiState.currentDate)
                                )
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
        val currentDate: LocalDate = CalendarUtil.todayLocalDate,
        val habitCalendar: List<HabitCalendar>,
        val habits: List<HabitDataEntity> = emptyList()
    ) : HomeUiState

    data object Loading : HomeUiState
    data class Error(val appError: AppError) : HomeUiState
}
