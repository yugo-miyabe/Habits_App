package jp.yuyuyu.habits.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
                // TODO エラー表示
            }

            is HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
