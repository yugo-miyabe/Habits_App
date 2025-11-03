package jp.yuyuyu.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import jp.yuyuyu.habits.screen.addHabit.AddHabitScreen
import jp.yuyuyu.habits.screen.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

@Serializable
object AddHabitRoute

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {
    composable<HomeRoute> {
        HomeScreen(
            onSettingClick = navHostController::navigateToSetting,
            onAddHabitClick = navHostController::navigateToAddHabit,
        )
    }

    composable<AddHabitRoute>() {
        AddHabitScreen(
            onBackClick = navHostController::popBackStack
        )
    }
}

fun NavController.navigateToAddHabit() = navigate(route = AddHabitRoute)