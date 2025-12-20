package jp.yuyuyu.habits.screen.home

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.ui.model.DialogType
import jp.yuyuyu.habits.ui.molecules.ProgressIndicator
import jp.yuyuyu.habits.ui.organisms.CommonDialog
import jp.yuyuyu.habits.ui.template.HomeTemplate
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
    onAddHabitClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.getAllHabits()
    }

    uiState.value.let { state ->
        when (state) {
            is HomeUiState.Success -> {
                HomeTemplate(
                    habitCalendarList = state.habitCalendar,
                    onClickCalendar = { calendar ->
                        viewModel.onClickCalendar(calendar)
                    },
                    updateHabitCompletion = {

                    },
                    nextMonth = {
                        viewModel.onNextMonth(it)
                    },
                    prevMoth = {
                        viewModel.onPrevMonth(it)
                    },
                    onAddHabitClick = {
                        onAddHabitClick()
                    },
                    onSettingClick = onSettingClick
                )
            }

            is HomeUiState.Error -> {
                when (state.appError) {
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

            is HomeUiState.Loading -> {
                ProgressIndicator(
                    modifier = Modifier.size(200.dp),
                )
            }
        }
    }
}
