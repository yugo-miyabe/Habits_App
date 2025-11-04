package jp.yuyuyu.habits.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import jp.yuyuyu.habits.screen.license.LicenseInfoScreen
import jp.yuyuyu.habits.screen.setting.SettingScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingRoute

@Serializable
object LicenseInfoRoute

fun NavGraphBuilder.settingNavGraph(navHostController: NavHostController) {
    composable<SettingRoute> {
        SettingScreen(
            onBackClick = navHostController::popBackStack,
            onLicenseInfoClick = navHostController::navigateToLicenseInfo
        )
    }

    composable<LicenseInfoRoute> {
        LicenseInfoScreen(
            onBackClick = navHostController::popBackStack
        )
    }
}

fun NavController.navigateToSetting(
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) = navigate(
    route = SettingRoute,
    navOptions = builder?.let(::navOptions)
)

fun NavController.navigateToLicenseInfo(
    builder: (NavOptionsBuilder.() -> Unit)? = null,
) = navigate(
    route = LicenseInfoRoute,
    navOptions = builder?.let(::navOptions)
)
