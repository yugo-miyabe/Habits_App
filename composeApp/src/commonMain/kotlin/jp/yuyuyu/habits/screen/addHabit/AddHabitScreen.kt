package jp.yuyuyu.habits.screen.addHabit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import jp.yuyuyu.habits.ui.template.AddHabitTemplate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddHabitScreen(
    onBackClick: () -> Unit,
    onAddHabitClick: () -> Unit,
    viewModel: AddHabitViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.isAddHabitSuccess) {
        onAddHabitClick()
    }

    AddHabitTemplate(
        onBackClick = onBackClick,
        addHabitText = uiState.value.addHabitText,
        onAddHabitClick = {
            viewModel.onAddHabit()
        },
        onTextChange = { text ->
            viewModel.onChangeAddHabitText(text)
        },
        isAddHabitButtonEnable = uiState.value.isAddHabitButtonEnable,
    )
}
