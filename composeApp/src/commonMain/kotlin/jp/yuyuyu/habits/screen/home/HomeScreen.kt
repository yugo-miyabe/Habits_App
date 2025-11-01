package jp.yuyuyu.habits.screen.home

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import jp.yuyuyu.habits.ui.template.HomeTemplate
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState

    val pager = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE },
    )

    uiState.value.let { state ->
        when (state) {
            is HomeUiState.Success -> {
                HomeTemplate(
                    calendarPagerState = pager,
                    currentDateTime = state.currentDateTime,
                    onSettingClick = onSettingClick
                )
            }

            is HomeUiState.Error -> {
                // TODO エラー表示
            }

            is HomeUiState.Loading -> {
                // TODO ローディング表示
            }
        }
    }
}
