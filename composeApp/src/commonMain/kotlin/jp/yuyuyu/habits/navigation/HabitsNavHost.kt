package jp.yuyuyu.habits.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun HabitsNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeNavGraph(navigationToSetting = navController::navigateToSetting)

        settingNavGraph()
    }
}