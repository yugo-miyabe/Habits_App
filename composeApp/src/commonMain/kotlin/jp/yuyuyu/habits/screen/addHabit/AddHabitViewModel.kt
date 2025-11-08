package jp.yuyuyu.habits.screen.addHabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.repository.HabitDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddHabitViewModel(
    val habitDatabaseRepository: HabitDatabaseRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddHabitUiState())
    val uiState: StateFlow<AddHabitUiState> = _uiState

    fun onChangeAddHabitText(text: String) {
        _uiState.value = uiState.value.copy(addHabitText = text)
    }

    fun onAddHabit() {
        viewModelScope.launch(Dispatchers.IO) {
            habitDatabaseRepository.insertHabit(habitName = uiState.value.addHabitText)
        }
        _uiState.value = uiState.value.copy(isAddHabitSuccess = true)
    }
}

data class AddHabitUiState(
    val isAddHabitSuccess: Boolean = false,
    val addHabitText: String = "",
) {
    val isAddHabitButtonEnable: Boolean = addHabitText != ""
}
