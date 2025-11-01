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
    val pager = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE },
    )

    HomeTemplate(
        calendarPagerState = pager,
        onSettingClick = onSettingClick
    )
}
