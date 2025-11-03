package jp.yuyuyu.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.yuyuyu.habits.screen.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

@Serializable
object AddHabitRoute

fun NavGraphBuilder.homeNavGraph(navigationToSetting: () -> Unit) {
    composable<HomeRoute> {
        HomeScreen(
            onSettingClick = navigationToSetting,
            onAddHabitClick = {
                // TODO 習慣追加画面へ遷移
            },
        )
    }

    composable<AddHabitRoute>() {
        // TODO 習慣追加画面
    }
}

fun NavController.navigateToAddHabit() = navigate(route = AddHabitRoute)