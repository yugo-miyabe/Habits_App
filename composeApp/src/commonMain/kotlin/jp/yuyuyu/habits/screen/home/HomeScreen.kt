package jp.yuyuyu.habits.screen.home

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.yuyuyu.habits.AppError
import jp.yuyuyu.habits.ui.molecules.ProgressIndicator
import jp.yuyuyu.habits.ui.template.HomeTemplate
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
    onAddHabitClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val pager = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE },
    )

    uiState.value.let { state ->
        when (state) {
            is HomeUiState.Success -> {
                HomeTemplate(
                    calendarPagerState = pager,
                    currentDate = state.currentDate,
                    habitCalendarList = state.habitCalendar,
                    nextMonth = {
                        viewModel.onNextMonth()
                    },
                    prevMoth = {
                        viewModel.onPrevMonth()
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
                        // TODO Show DataBase Error Dialog
                    }

                    AppError.NetworkError -> {
                        // TODO Show Network Error Dialog
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
