package jp.yuyuyu.habits.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.yuyuyu.habits.screen.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

fun NavGraphBuilder.homeNavGraph(navigationToSetting: () -> Unit) {
    composable<HomeRoute> {
        HomeScreen(onSettingClick = navigationToSetting)
    }
}