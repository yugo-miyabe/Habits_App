package jp.yuyuyu.habits.screen.addHabit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.ui.model.DialogType
import jp.yuyuyu.habits.ui.organisms.CommonDialog
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

    uiState.value.appError?.let { state ->
        when (state) {
            AppError.DataBaseError -> {
                CommonDialog(
                    onDismiss = viewModel::dismissErrorDialog,
                    dialogType = DialogType.DatabaseError
                )
            }

            AppError.NetworkError -> {
                CommonDialog(
                    onDismiss = viewModel::dismissErrorDialog,
                    dialogType = DialogType.NetworkError
                )
            }
        }
    }
}
