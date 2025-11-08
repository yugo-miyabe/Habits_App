package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeViewModel(
    val habitDatabaseRepository: HabitDatabaseRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            habitDatabaseRepository.insertHabit(
                "朝の散歩",
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            delay(1500L)
            _uiState.value = HomeUiState.Success(CalendarUtil.todayLocalDate, listOf())
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
        val habits: List<HabitDataEntity> = emptyList()
    ) : HomeUiState

    data object Loading : HomeUiState
    data object Error : HomeUiState
}
