package jp.yuyuyu.habits.screen.addHabit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddHabitViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(AddHabitUiState())
    val uiState: StateFlow<AddHabitUiState> = _uiState

    fun onChangeAddHabitText(text: String) {
        _uiState.value = uiState.value.copy(addHabitText = text)
    }

    fun onAddHabit() {
        // TODO 習慣追加処理
        _uiState.value = uiState.value.copy(isAddHabitSuccess = true)
    }
}

data class AddHabitUiState(
    val isAddHabitSuccess: Boolean = false,
    val addHabitText: String = "",
) {
    val isAddHabitButtonEnable: Boolean = addHabitText != ""
}
