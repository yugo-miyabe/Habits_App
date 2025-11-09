package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.ui.model.CalendarWeek
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
                        _uiState.value = HomeUiState.Success(
                            currentDate = CalendarUtil.todayLocalDate,
                            calenderList = CalendarUtil.createMonthUIModels(CalendarUtil.todayLocalDate),
                            habits = habits
                        )
                    }
                )
            }
        }
    }

    fun onNextMonth() {
        _uiState.update { uiState ->
            when (uiState) {
                is HomeUiState.Success -> {
                    uiState.copy(
                        currentDate = CalendarUtil.plusOneMonth(uiState.currentDate),
                    )
                }

                else -> uiState
            }
        }
    }

    fun onPrevMonth() {
        _uiState.update { uiState ->
            when (uiState) {
                is HomeUiState.Success -> uiState.copy(
                    currentDate = CalendarUtil.minusOneMonth(uiState.currentDate)
                )

                else -> uiState
            }
        }
    }
}

sealed interface HomeUiState {
    data class Success(
        val currentDate: LocalDate = CalendarUtil.todayLocalDate,
        val calenderList: List<CalendarWeek> = emptyList(),
        val habits: List<HabitDataEntity> = emptyList()
    ) : HomeUiState

    data object Loading : HomeUiState
    data class Error(val appError: AppError) : HomeUiState
}
