package jp.yuyuyu.habits.screen.addHabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.usecase.InsertHabitUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddHabitViewModel(
    val insertHabitUseCase: InsertHabitUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddHabitUiState())
    val uiState: StateFlow<AddHabitUiState> = _uiState

    fun onChangeAddHabitText(text: String) {
        _uiState.value = uiState.value.copy(addHabitText = text)
    }

    fun onAddHabit() = viewModelScope.launch(Dispatchers.IO) {
        insertHabitUseCase(uiState.value.addHabitText).collect { result ->
            result.fold(
                ifLeft = {
                    _uiState.value = uiState.value.copy(appError = it)
                },
                ifRight = {
                    _uiState.value = uiState.value.copy(isAddHabitSuccess = true)
                }
            )
        }
    }
}

data class AddHabitUiState(
    val isAddHabitSuccess: Boolean = false,
    val addHabitText: String = "",
    val appError: AppError? = null,
) {
    val isAddHabitButtonEnable: Boolean = addHabitText != ""
}
