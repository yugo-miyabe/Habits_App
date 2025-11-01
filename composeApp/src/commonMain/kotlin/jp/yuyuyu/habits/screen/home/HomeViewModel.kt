package jp.yuyuyu.habits.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.database.AppDatabase
import jp.yuyuyu.habits.database.HabitDataEntity
import jp.yuyuyu.habits.database.HabitDay
import jp.yuyuyu.habits.util.CalendarUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeViewModel(
    appDatabase: AppDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState.Success())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.getDao().insert(
                HabitDataEntity(
                    title = "Sample Habit",
                    calendar = listOf(
                        HabitDay(date = "2024-01-01", isSelected = true),
                        HabitDay(date = "2024-01-02", isSelected = true)
                    )
                )
            )
        }
    }

    fun onNextMonth() {
        _uiState.value.currentDate
        _uiState.update { uiState ->
            uiState.copy(
                currentDate = CalendarUtil.plusOneMonth(uiState.currentDate),
            )
        }
        _uiState.value.currentDate
    }

    fun onPrevMonth() {
        _uiState.update { uiState ->
            uiState.copy(
                currentDate = CalendarUtil.minusOneMonth(uiState.currentDate)
            )
        }
    }
}

sealed class HomeUiState {
    data class Success(
        val currentDate: LocalDate = CalendarUtil.todayLocalDate,
        val habits: List<HabitDataEntity> = emptyList()
    ) : HomeUiState()

    class Loading : HomeUiState()

    data class Error(
        val exception: Throwable
    ) : HomeUiState()
}
