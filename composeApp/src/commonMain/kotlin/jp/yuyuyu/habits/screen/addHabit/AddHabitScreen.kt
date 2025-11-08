package jp.yuyuyu.habits.screen.addHabit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.ui.template.AddHabitTemplate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddHabitScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddHabitViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.isAddHabitSuccess) {
        onNavigateBack()
    }

    AddHabitTemplate(
        onBackClick = onNavigateBack,
        addHabitText = uiState.value.addHabitText,
        onAddHabitClick = viewModel::onAddHabit,
        onTextChange = { text ->
            viewModel.onChangeAddHabitText(text)
        },
        isAddHabitButtonEnable = uiState.value.isAddHabitButtonEnable,
    )

    uiState.value.appError?.let { it ->
        when (it) {
            AppError.DataBaseError -> {
                // TODO Show DataBase Error Dialog
            }

            AppError.NetworkError -> {
                // TODO Show Network Error Dialog
            }
        }
    }
}
