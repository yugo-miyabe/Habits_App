package jp.yuyuyu.habits.screen.habitManage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.ui.model.DialogType
import jp.yuyuyu.habits.ui.organisms.CommonDialog
import jp.yuyuyu.habits.ui.template.HabitManageTemplate
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HabitManageScreen(
    onBackClick: () -> Unit,
    viewModel: HabitManageViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    HabitManageTemplate(
        habitList = uiState.value.habitList,
        onBackClick = onBackClick,
    )

    uiState.value.appError?.let { appError ->
        when (appError) {
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