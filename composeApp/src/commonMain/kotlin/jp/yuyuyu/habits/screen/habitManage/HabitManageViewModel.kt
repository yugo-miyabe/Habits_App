package jp.yuyuyu.habits.screen.habitManage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.usecase.GetAllHabitUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HabitManageViewModel(
    val getAllHabitUseCase: GetAllHabitUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HabitManageUiState())
    val uiState: StateFlow<HabitManageUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllHabitUseCase().collect { result ->
                result.fold(
                    ifLeft = {
                        _uiState.value = uiState.value.copy(appError = it)
                    },
                    ifRight = { habitList ->
                        _uiState.value = uiState.value.copy(
                            habitList = habitList.map { habit ->
                                HabitManageUiState.HabitManageItem(
                                    habitId = habit.id,
                                    title = habit.title
                                )
                            }
                        )
                    },
                )
            }
        }
    }
}

data class HabitManageUiState(
    val habitList: List<HabitManageItem> = emptyList(),
    val appError: AppError? = null,
    ) {
    data class HabitManageItem(
        val habitId: Long,
        val title: String,
    )
}
