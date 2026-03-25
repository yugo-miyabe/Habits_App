package jp.yuyuyu.habits.screen.habitManage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.usecase.DeleteHabitUseCase
import jp.yuyuyu.habits.usecase.GetAllHabitUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HabitManageViewModel(
    val getAllHabitUseCase: GetAllHabitUseCase,
    val deleteHabitUseCase: DeleteHabitUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HabitManageUiState())
    val uiState: StateFlow<HabitManageUiState> = _uiState

    init {
        getHabitList()
    }

    fun deleteHabit() = viewModelScope.launch(Dispatchers.IO) {
        val habitId = uiState.value.deleteHabitId ?: return@launch
        deleteHabitUseCase(habitId).collect { result ->
            result.fold(
                ifLeft = {
                    _uiState.value = uiState.value.copy(appError = it)
                },
                ifRight = {
                    _uiState.update { uiState ->
                        uiState.copy(deleteHabitId = null)
                    }
                    getHabitList()
                },
            )
        }
    }

    fun showDeleteHabitDialog(habitId: Long) {
        _uiState.update { uiState ->
            uiState.copy(deleteHabitId = habitId)
        }
    }

    fun dismissDeleteHabitDialog() {
        _uiState.update { uiState ->
            uiState.copy(deleteHabitId = null)
        }
    }

    fun dismissErrorDialog() {
        _uiState.update { uiState ->
            uiState.copy(appError = null)
        }
    }

    private fun getHabitList() = viewModelScope.launch(Dispatchers.IO) {
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

data class HabitManageUiState(
    val habitList: List<HabitManageItem> = emptyList(),
    val appError: AppError? = null,
    val deleteHabitId: Long? = null,
) {
    data class HabitManageItem(
        val habitId: Long,
        val title: String,
    )
}
