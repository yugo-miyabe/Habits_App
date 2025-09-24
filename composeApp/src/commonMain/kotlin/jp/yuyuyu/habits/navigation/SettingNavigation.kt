package jp.yuyuyu.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import jp.yuyuyu.habits.screen.setting.SettingScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingRoute

fun NavGraphBuilder.settingNavGraph() {
    composable<SettingRoute> {
        SettingScreen()
    }
}

fun NavController.navigateToSetting(
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) = navigate(
    route = SettingRoute,
    navOptions = builder?.let(::navOptions)
)
